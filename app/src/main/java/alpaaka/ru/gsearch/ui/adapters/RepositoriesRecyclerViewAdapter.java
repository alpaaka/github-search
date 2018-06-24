package alpaaka.ru.gsearch.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import alpaaka.ru.gsearch.R;
import alpaaka.ru.gsearch.data.model.Repository;

public class RepositoriesRecyclerViewAdapter
        extends RecyclerView.Adapter<RepositoriesRecyclerViewAdapter.RepositoryHolder> {

    private Context context;
    private ArrayList<Repository> list;

    public RepositoriesRecyclerViewAdapter(Context context, ArrayList<Repository> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RepositoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_repository_item, viewGroup, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryHolder repositoryHolder, int i) {
        repositoryHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void loadData(ArrayList<Repository> list, boolean refresh) {
        if (refresh) {
            notifyItemRangeRemoved(0, this.list.size());
            this.list.clear();
        }
        int position = this.list.size();
        this.list.addAll(list);
        notifyItemRangeInserted(position, list.size());
    }

    class RepositoryHolder extends RecyclerView.ViewHolder {

        private ImageView profileIcon;
        private TextView repTitle;
        private TextView repDescription;

        RepositoryHolder(@NonNull View itemView) {
            super(itemView);
            profileIcon = itemView.findViewById(R.id.iv_profile_icon);
            repTitle = itemView.findViewById(R.id.tv_repository_name);
            repDescription = itemView.findViewById(R.id.tv_repository_description);
        }

        void bind(Repository repository) {
            repTitle.setText(repository.getTitle());
            repDescription.setText(repository.getDescription());
            Picasso.get()
                    .load(repository.getOwner().getAvatarUrl())
                    .fit()
                    .into(profileIcon);
        }
    }
}
