package com.pormob.aq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.pormob.aq.api.ArticleContainerHelper;
import com.pormob.aq.base.BaseActivity;
import com.pormob.aq.models.Article;

public class ArticleActivity extends BaseActivity implements OnSuccessListener {
    private Toolbar toolbar;
    private ActionBar actionBar;
    private WebView webView;
    private Activity activity;
    private FloatingActionButton floatingActionButtonShare;
    private FloatingActionButton floatingActionButtonComment;
    private TextView textViewShare;
    private TextView textViewComment;
    private Article article;
    private static final int SHARE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        this.activity = this;
        ArticleContainerHelper.getArticleContainer().addOnSuccessListener(this);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_article;
    }

    protected void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_article_toolbar);
        setSupportActionBar(toolbar);
        this.actionBar = getSupportActionBar();
        this.actionBar.setDisplayHomeAsUpEnabled(true);
        this.actionBar.setTitle(article.getTitre());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureWebView() {
        this.webView = findViewById(R.id.webView);
        this.webView.loadDataWithBaseURL(null, article.getValeurArticle(), "text/html", "utf-8", null);
        this.webView.setBackgroundColor(0x00000000);
    }

    private void configureActivity() {
        this.floatingActionButtonComment = findViewById(R.id.activity_new_floating_action_button_comment);
        this.floatingActionButtonShare = findViewById(R.id.activity_new_floating_action_button_share);
        this.textViewComment = findViewById(R.id.textview_comment);
        if (article.getNbCommentaire() > 0) {
            this.textViewComment.setText(String.valueOf(article.getNbCommentaire()));
        }
        this.textViewShare = findViewById(R.id.textview_share);
        if (article.getNbPartage() > 0) {
            this.textViewShare.setText(String.valueOf(article.getNbPartage()));
        }
        this.floatingActionButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCurrentUserLogged()) {
                    Intent layout1 = new Intent(activity, CommentaireActivity.class);
                    layout1.putExtra("nbCommentaire", article.getNbCommentaire());
                    startActivity(layout1);
                } else {
                    new AlertDialog.Builder(activity).setMessage(R.string.popup_message_floating_button_Comment).setNeutralButton(R.string.popup_message_choice_ok, null).show();
                }
            }
        });

        this.floatingActionButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String html = article.getValeurArticle();
                html = html.replace("<title>Article</title>", "");
                html = html.replace("<style>     .article {         margin: 0;         padding: .3rem;         font: 1rem 'Fira Sans', sans-serif;     }      .main_review {         margin: .5rem;         padding: .3rem;         font-size: 1.2rem;         background-color: #B9BFE9;     }      </style>", "");
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, article.getTitre() + ", of " + article.getAuteur() + " from the app AQ");
                sendIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(html));
                sendIntent.setType("text/html");
                Intent shareIntent = Intent.createChooser(sendIntent, "Share");
                startActivityForResult(shareIntent, SHARE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("MSG", "ArticleActivity  requestCode = " + Integer.toString(requestCode) + ", resultCode = " + Integer.toString(resultCode));
        if (requestCode == SHARE) {
            ArticleContainerHelper.updatePartage(article.getNbPartage() + 1);
            this.textViewShare.setText(String.valueOf(article.getNbPartage() + 1));
        }
    }

    @Nullable
    protected FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    protected Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() != null);
    }

    @Override
    public void onSuccess(Object o) {
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) o;
        Article a = documentSnapshot.toObject(Article.class);
        this.article = a;
        this.configureToolbar();
        this.configureWebView();
        this.configureActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArticleContainerHelper.getArticleContainer().addOnSuccessListener(this);
    }
}