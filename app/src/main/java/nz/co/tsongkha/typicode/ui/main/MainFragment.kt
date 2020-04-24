package nz.co.tsongkha.typicode.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.yelp.android.bento.componentcontrollers.RecyclerViewComponentController
import com.yelp.android.bento.components.ListComponent
import nz.co.tsongkha.R
import nz.co.tsongkha.typicode.post.Post
import nz.co.tsongkha.typicode.ui.main.bento.PostViewHolder
import nz.co.tsongkha.typicode.ui.main.bento.PostViewProps

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var component: ListComponent<Nothing?, PostViewProps>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val controller = RecyclerViewComponentController(recyclerView)
        val component = ListComponent<Nothing?, PostViewProps>(
            null,
            PostViewHolder::class.java
        )
        controller.addComponent(component)

        viewModel.posts.observe(this, object: Observer<List<PostViewProps>> {
            override fun onChanged(t: List<PostViewProps>?) {
                if (t == null) return
                component.setData(t)
            }
        })
    }
