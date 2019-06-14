package id.dana.onboarding.movie.internal.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import id.dana.onboarding.movie.internal.component.MovieComponent;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version PopularMovieFragment, v 0.1 2019-06-14 16:05 by Derayan Bima A
 */
public class PopularMovieFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(MovieComponent.class).inject(this);
    }
}
