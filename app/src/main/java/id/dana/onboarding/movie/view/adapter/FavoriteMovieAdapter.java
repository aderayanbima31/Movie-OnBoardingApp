package id.dana.onboarding.movie.view.adapter;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.dana.onboarding.movie.R;
import id.dana.onboarding.movie.model.FavoriteMovieModel;
import id.dana.onboarding.movie.navigation.Navigator;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version FavoriteMovieAdapter, v 0.1 2019-06-17 22:06 by Derayan Bima A
 */
public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder> {

    private static final String TAG = FavoriteMovieAdapter.class.getSimpleName();

    private final LayoutInflater layoutInflater;

    private Context context;

    private List<FavoriteMovieModel> favoriteMoviesCollection;

    private FavoriteMovieAdapter.OnItemClickListener onItemClickListener;

    @Inject
    FavoriteMovieAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.favoriteMoviesCollection = Collections.emptyList();
        this.context = context;

    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater
            .inflate(R.layout.list_item_favorite_movie, parent, false);
        return new FavoriteMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int position) {
        FavoriteMovieModel favoriteMovieModel = this.favoriteMoviesCollection.get(position);
        Glide.with(context).load(favoriteMovieModel.getPosterPath())
            .into(holder.thumbnailPopularMovie);
        holder.textMovieTitle.setText(favoriteMovieModel.getOriginalTitle());
        holder.textMovieRate
            .setText(String.format(context.getString(R.string.format_rate_value), String.valueOf(favoriteMovieModel.getVoteAverage())));
        holder.thumbnailPopularMovie.setOnClickListener(v -> {
            if (FavoriteMovieAdapter.this.onItemClickListener != null) {
                FavoriteMovieAdapter.this.onItemClickListener
                    .onMovieItemClicked(favoriteMovieModel);
                Navigator navigator = new Navigator();
                navigator.navigateToDetailMovie(context, favoriteMovieModel.getMovieId());
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.favoriteMoviesCollection != null) ? this.favoriteMoviesCollection.size() : 0;
    }

    public void setMoviesCollection(Collection<FavoriteMovieModel> moviesCollection) {
        this.validateMoviesCollection(moviesCollection);
        this.favoriteMoviesCollection = (List<FavoriteMovieModel>) moviesCollection;

        this.notifyDataSetChanged();
    }

    private void validateMoviesCollection(Collection<FavoriteMovieModel> moviesCollection) {
        if (moviesCollection == null) {
            throw new IllegalArgumentException("The list is empty");
        }
    }

    public void setOnItemClickListener(
        FavoriteMovieAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onMovieItemClicked(FavoriteMovieModel favoriteMovieModel);
    }

    static class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_favorite_movie_rate)
        TextView textMovieRate;

        @BindView(R.id.tv_favorite_movie_title)
        TextView textMovieTitle;

        @BindView(R.id.iv_thumbnail_favorite_movie)
        ImageView thumbnailPopularMovie;

        FavoriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

