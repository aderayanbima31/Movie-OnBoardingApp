package id.dana.onboarding.movie.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.dana.onboarding.movie.R;
import id.dana.onboarding.movie.model.MovieTrailerModel;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieTrailerAdapter, v 0.1 2019-06-18 09:05 by Derayan Bima A
 */
public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.VideoTrailerViewHolder> {

    private final LayoutInflater layoutInflater;

    private Context context;

    private List<MovieTrailerModel> movieTrailerModels;

    private OnItemClickListener onItemClickListener;

    @Inject
    public MovieTrailerAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.movieTrailerModels = Collections.emptyList();
    }

    @NonNull
    @Override

    public VideoTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.layoutInflater
            .inflate(R.layout.list_item_movie_trailer, parent, false);
        return new VideoTrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoTrailerViewHolder holder, int position) {
        final MovieTrailerModel movieTrailerModel = movieTrailerModels.get(position);
        String youtubeBuiltVideoUrl = isSiteYoutube(movieTrailerModel.getSite());

        if (youtubeBuiltVideoUrl != null) {
            StringBuilder urlBuilt = new StringBuilder().append(youtubeBuiltVideoUrl)
                .append(movieTrailerModel.getKey());
            holder.tvVideoTrailer.setText(urlBuilt);
        }

        holder.tvVideoTrailer.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onVideoUrlItemClicked(movieTrailerModel);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return movieTrailerModels != null ? movieTrailerModels.size() : 0;
    }

    public void setVideoTrailerList(List<MovieTrailerModel> movieTrailerModels) {
        validateVideoTrailerList(movieTrailerModels);
        this.movieTrailerModels = movieTrailerModels;
        Log.e("TAG", "setVideoTrailerList: " + movieTrailerModels.size());
        notifyDataSetChanged();
    }

    public void validateVideoTrailerList(List<MovieTrailerModel> videoModels) {
        if (videoModels == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    private String isSiteYoutube(String site) {
        String urlBuilt = "";
        if (site.equalsIgnoreCase("youtube")) {
            urlBuilt = "https://www.youtube.com/watch?v=";
        } else {
            return null;
        }
        return urlBuilt;
    }

    public void setOnItemClickListener(
        OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onVideoUrlItemClicked(MovieTrailerModel videoModel);
    }

    public class VideoTrailerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_video_trailer_url)
        TextView tvVideoTrailer;

        public VideoTrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

