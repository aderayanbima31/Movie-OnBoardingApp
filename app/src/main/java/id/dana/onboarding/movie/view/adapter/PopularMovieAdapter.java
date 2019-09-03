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
import id.dana.onboarding.movie.model.MovieModel;
import id.dana.onboarding.movie.navigation.Navigator;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version PopularMovieAdapter, v 0.1 2019-06-17 22:21 by Derayan Bima A
 */
public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder> {

    private final LayoutInflater layoutInflater;

    private Context context;

    private List<MovieModel> moviesCollection;

    private OnItemClickListener onItemClickListener;

    private int count = 1;

    @Inject
    public PopularMovieAdapter(Context context){
        this.layoutInflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.moviesCollection = Collections.emptyList();
        this.context = context;
    }

    @NonNull
    @Override
    public PopularMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater
            .inflate(R.layout.item_list_popular_movie, parent, false);

        return new PopularMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieViewHolder holder, int position) {
        final MovieModel movieModel = this.moviesCollection.get(position);
        Glide.with(context).load(movieModel.getUrlPoster()).into(holder.thumbnailPopularMovie);
        holder.textMovieTitle.setText(movieModel.getTitle());
        holder.thumbnailPopularMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PopularMovieAdapter.this.onItemClickListener != null) {
                    PopularMovieAdapter.this.onItemClickListener.onMovieItemClicked(movieModel);
                    Navigator navigator = new Navigator();
                    navigator.navigateToDetailMovie(context, movieModel.getMovieId());
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.moviesCollection != null) ? this.moviesCollection.size() : 0;
    }

    private void validateMoviesCollection(Collection<MovieModel> moviesCollection){
        if (moviesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null!");
        }
    }

    public void setMoviesCollection(Collection<MovieModel> moviesCollection){
        this.validateMoviesCollection(moviesCollection);

        if (this.moviesCollection.isEmpty()) {
            this.moviesCollection = (List<MovieModel>) moviesCollection;
        }
        else {
            this.moviesCollection.addAll(moviesCollection);
        }
        this.notifyDataSetChanged();
        count++;
    }

    public void clearList() {
        this.moviesCollection.clear();
    }

    public interface OnItemClickListener {

        void onMovieItemClicked(MovieModel movieModel);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getPageCount() {
        return count;
    }

    static class PopularMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_movie_rate)
        TextView textMovieRate;

        @BindView(R.id.tv_movie_title)
        TextView textMovieTitle;

        @BindView(R.id.iv_thumbnail_popular_movie)
        ImageView thumbnailPopularMovie;

        PopularMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
