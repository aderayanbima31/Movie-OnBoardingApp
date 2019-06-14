package id.dana.onboarding.movie.internal.view.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import id.dana.onboarding.movie.internal.HasComponent;

/**
 * @author Derayan Bima A (derayan.bima@dana.id)
 * @version BaseFragment, v 0.1 2019-06-14 16:04 by Derayan Bima A
 */
public abstract class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for Dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getContext()).getComponent());
    }

}
