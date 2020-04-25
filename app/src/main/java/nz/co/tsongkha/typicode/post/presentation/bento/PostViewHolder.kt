package nz.co.tsongkha.typicode.post.presentation.bento

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.yelp.android.bento.componentcontrollers.RecyclerViewComponentController
import com.yelp.android.bento.components.ListComponent
import com.yelp.android.bento.core.ComponentViewHolder
import com.yelp.android.bento.utils.inflate
import nz.co.tsongkha.R

class PostViewHolder : ComponentViewHolder<PostPresenter, PostItem>() {

    private lateinit var cardView: CardView

    private lateinit var titleTextView: TextView

    private lateinit var bodyTextView: TextView

    private lateinit var commentsRecyclerView: RecyclerView

    override fun inflate(parent: ViewGroup): View {
        return parent.inflate<CardView>(R.layout.item_post).apply {
            cardView = this
            titleTextView = findViewById(R.id.titleTextView)
            bodyTextView = findViewById(R.id.bodyTextView)
            commentsRecyclerView = findViewById(R.id.commentsRecyclerView)
        }
    }

    override fun bind(presenter: PostPresenter, element: PostItem) {
        cardView.setOnClickListener {
            presenter.onPostClick(element.id)
        }

        titleTextView.text = element.title
        bodyTextView.text = element.body

        val controller = RecyclerViewComponentController(commentsRecyclerView)
        val component = ListComponent<Nothing?, CommentItem>(
            null,
            CommentViewHolder::class.java
        )
        controller.addComponent(component)
        component.setData(element.commentsSection.comments)
    }
}