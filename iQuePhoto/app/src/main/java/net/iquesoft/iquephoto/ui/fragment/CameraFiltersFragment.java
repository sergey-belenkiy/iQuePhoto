package net.iquesoft.iquephoto.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.iquesoft.iquephoto.R;
import net.iquesoft.iquephoto.adapter.FiltersAdapter;
import net.iquesoft.iquephoto.common.BaseFragment;
import net.iquesoft.iquephoto.di.components.ICameraActivityComponent;
import net.iquesoft.iquephoto.model.Filter;
import net.iquesoft.iquephoto.presentation.presenter.fragment.CameraFiltersPresenterImpl;
import net.iquesoft.iquephoto.presentation.view.fragment.CameraFiltersView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CameraFiltersFragment extends BaseFragment implements CameraFiltersView {

    private Unbinder mUnbinder;

    @Inject
    CameraFiltersPresenterImpl presenter;

    @BindView(R.id.cameraFiltersRecyclerView)
    RecyclerView recyclerView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getComponent(ICameraActivityComponent.class).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_filters, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setupFiltersAdapter(List<Filter> filters) {
        FiltersAdapter adapter = new FiltersAdapter(filters);

        adapter.setFiltersListener(filter -> {
            //TODO: mImageEditorView.setFilter(filter.getColorMatrix());
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(null, LinearLayout.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}
