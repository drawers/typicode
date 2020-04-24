package nz.co.tsongkha.typicode.ui.main.bento

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.yelp.android.bento.core.ComponentViewHolder
import com.yelp.android.bento.utils.inflate
import nz.co.tsongkha.R

class PostViewHolder : ComponentViewHolder<Nothing?, PostViewProps>() {

    private lateinit var titleTextView: TextView
    private lateinit var bodyTextView: TextView

    override fun inflate(parent: ViewGroup): View {
        return parent.inflate<CardView>(R.layout.item_post).apply {
            titleTextView = findViewById(R.id.titleTextView)
            bodyTextView = findViewById(R.id.bodyTextView)
        }
    }

    override fun bind(presenter: Nothing?, element: PostViewProps) {
        titleTextView.text = element.title
        bodyTextView.text = element.body
    }
}