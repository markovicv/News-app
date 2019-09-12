package com.example.newsapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.newsapp.R;
import com.example.newsapp.listeners.OnNewsItemClickListener;
import com.example.newsapp.model.Article;
import com.example.newsapp.utils.TimeParser;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> articles = new ArrayList<>();
    private Context context;
    private OnNewsItemClickListener onNewsItemClickListener;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,onNewsItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.author.setText(article.getAuthor());
        holder.source.setText(article.getSource().getName());
        holder.time.setText(TimeParser.dateToTime(article.getPublishedAt()));
        holder.description.setText(article.getDescription());
        holder.published.setText(TimeParser.dateFormat(article.getPublishedAt()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(TimeParser.getRandomDrawbleColor());
        requestOptions.error(TimeParser.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();


        Glide.with(context)
                .load(article.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setOnNewsItemClickListener(OnNewsItemClickListener onNewsItemClickListener) {
        this.onNewsItemClickListener = onNewsItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView title;
        private TextView description;
        private TextView author;
        private TextView published;
        private TextView source;
        private TextView time;
        private ImageView imageView;
        private ProgressBar progressBar;
        private OnNewsItemClickListener onNewsItemClickListener;


        public ViewHolder(@NonNull View itemView, final OnNewsItemClickListener onNewsItemClickListener) {
            super(itemView);
            this.onNewsItemClickListener = onNewsItemClickListener;
            title = itemView.findViewById(R.id.titleId);
            author = itemView.findViewById(R.id.authorId);
            description = itemView.findViewById(R.id.descriptionId);
            published = itemView.findViewById(R.id.publishedId);
            source = itemView.findViewById(R.id.sourceId);
            time = itemView.findViewById(R.id.timeId);
            imageView = itemView.findViewById(R.id.mainImgId);
            progressBar = itemView.findViewById(R.id.progressBarPhotoId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNewsItemClickListener.onNewsClick(articles.get(getAdapterPosition()));
                }
            });

        }
    }
}
