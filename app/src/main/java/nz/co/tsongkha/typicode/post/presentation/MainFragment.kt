package nz.co.tsongkha.typicode.post.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.yelp.android.bento.componentcontrollers.RecyclerViewComponentController
import com.yelp.android.bento.components.ListComponent
import nz.co.tsongkha.R
import nz.co.tsongkha.typicode.common.ApplicationScope
import nz.co.tsongkha.typicode.common.ViewModelScope
import nz.co.tsongkha.typicode.post.presentation.bento.PostPresenter
import nz.co.tsongkha.typicode.post.presentation.bento.PostViewHolder
import toothpick.Scope
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by inject<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        KTP.openScope(ApplicationScope::class.java)
            .openSubScope(ViewModelScope::class.java) { scope: Scope ->
                scope.installViewModelBinding<MainViewModel>(this)
                    .closeOnViewModelCleared(this)
            }
            .openSubScope(this)
            .closeOnDestroy(this)
            .inject(this)

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        val controller = RecyclerViewComponentController(recyclerView)

        val component = ListComponent(
            PostPresenter(
                {
                    viewModel.loadComments(it)
                },
                {
                    viewModel.hideComments(it)
                }
            ),
            PostViewHolder::class.java
        ).apply {
            toggleDivider(false)
        }

        controller.addComponent(component)

        viewModel.posts.observe(viewLifecycleOwner, Observer { viewProps ->
            if (viewProps == null) return@Observer

            component.setData(viewProps)
        })

        viewModel.load()
    }
}