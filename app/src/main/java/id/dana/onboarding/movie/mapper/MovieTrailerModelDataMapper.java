package id.dana.onboarding.movie.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import id.dana.onboarding.domain.Video;
import id.dana.onboarding.movie.model.MovieTrailerModel;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version MovieTrailerModelDataMapper, v 0.1 2019-06-17 11:10 by Derayan Bima A
 */
public class MovieTrailerModelDataMapper {

    @Inject
    public MovieTrailerModelDataMapper(){

    }

    public List<MovieTrailerModel> mapper(List<Video> videos){
        List<MovieTrailerModel> movieTrailerModels = new ArrayList<>();

        for (int i=0; i < videos.size(); i++){
            MovieTrailerModel movieTrailerModel = new MovieTrailerModel(videos.get(i).getVideoId());
            movieTrailerModel.setKey(videos.get(i).getKey());
            movieTrailerModel.setName(videos.get(i).getName());
            movieTrailerModel.setSite(videos.get(i).getSite());
            movieTrailerModels.add(movieTrailerModel);
        }

        return movieTrailerModels;
    }



}
