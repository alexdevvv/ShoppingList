package com.example.shoppinglist.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShopItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()

        viewModel.shopList.observe(this) {
            /*
            Т.к.  у нас в адаптере теперь нет ссылки на лист, чтобы установить пришедшие данные в
            адаптер, мы используем метод submitList
             */
            adapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        adapter = ShopItemsAdapter()
        recyclerView.adapter = adapter
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShopItemsAdapter.ENABLED_VALUE,
            ShopItemsAdapter.MAX_POOL_SIZE
        )
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShopItemsAdapter.DISABLED_VALUE,
            ShopItemsAdapter.MAX_POOL_SIZE
        )

        initLongClick()
        initSwipe()
        initClick()

    }

    private fun initClick() {
        adapter.shopItemOnClickListener = {
            Toast.makeText(this, "${it.name}", Toast.LENGTH_LONG).show()

        }
    }

    private fun initSwipe() {
        val simpleItemToTouchCallbacks = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                /*
                Т.к.  у нас в адаптере теперь нет ссылки на лист, чтобы получить позицию в
                адаптере, мы используем метод currentList
                 */
                val item = adapter.currentList[viewHolder.adapterPosition]
                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.deleteShoItem(item)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemToTouchCallbacks)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun initLongClick() {
        adapter.shopItemOnLongClickListener = {
            viewModel.changeShopItem(it)
        }
    }


}
