package nz.co.tsongkha.typicode.post.presentation.bento

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.yelp.android.bento.core.ComponentViewHolder
import com.yelp.android.bento.utils.inflate
import nz.co.tsongkha.R

class CommentViewHolder : ComponentViewHolder<Nothing?, CommentItem>() {

    private lateinit var bodyTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var nameTextView: TextView

    override fun inflate(parent: ViewGroup): View {
        return parent.inflate<ConstraintLayout>(R.layout.item_comment).apply {
            bodyTextView = findViewById(R.id.bodyTextView)
            emailTextView = findViewById(R.id.emailTextView)
            nameTextView = findViewById(R.id.nameTextView)
        }
    }

    override fun bind(presenter: Nothing?, element: CommentItem) {
        bodyTextView.text = element.body
        emailTextView.text = element.email
        nameTextView.text = element.name
    }
}