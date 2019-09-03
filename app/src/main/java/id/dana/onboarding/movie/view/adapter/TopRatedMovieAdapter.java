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

public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieAdapter.TopRatedViewHolder> {

    private final LayoutInflater layoutInflater;

    private Context context;

    private List<MovieModel> moviesCollection;

    private OnItemClickListener onItemClickListener;

    private int count = 1;

    @Inject
    public TopRatedMovieAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.moviesCollection = Collections.emptyList();
        this.context = context;
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater
            .inflate(R.layout.list_item_top_rated_movie, parent, false);
        return new TopRatedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedViewHolder holder, int position) {
        final MovieModel movieModel = this.moviesCollection.get(position);
        Glide.with(context).load(movieModel.getUrlPoster()).into(holder.thumbnailTopRatedMovie);
        holder.textMovieTitleTopRated.setText(movieModel.getTitle());
        holder.textMovieVoteAverageTopRated
            .setText(String.format("%s/10", movieModel.getMovieRate()));
        holder.thumbnailTopRatedMovie.setOnClickListener(v -> {
            if (TopRatedMovieAdapter.this.onItemClickListener != null) {
                TopRatedMovieAdapter.this.onItemClickListener.onMovieItemClicked(movieModel);
                Navigator navigator = new Navigator();
                navigator.navigateToDetailMovie(context, movieModel.getMovieId());
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

    public void setMoviesCollection(Collection<MovieModel> moviesCollection) {
        validateMoviesCollection(moviesCollection);

        if (this.moviesCollection.isEmpty()) {
            this.moviesCollection = (List<MovieModel>) moviesCollection;
        }
        else {
            this.moviesCollection.addAll(moviesCollection);
        }
        this.notifyDataSetChanged();
        count++;
    }

    private void validateMoviesCollection(Collection<MovieModel> moviesCollection) {
        if (moviesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onMovieItemClicked(MovieModel movieModel);
    }

    public int getPageCount() {
        return count;
    }

    static class TopRatedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_movie_top_rated_title)
        TextView textMovieTitleTopRated;

        @BindView(R.id.tv_movie_top_rated_vote)
        TextView textMovieVoteAverageTopRated;

        @BindView(R.id.iv_thumbnail_top_rated_movie)
        ImageView thumbnailTopRatedMovie;

        TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
