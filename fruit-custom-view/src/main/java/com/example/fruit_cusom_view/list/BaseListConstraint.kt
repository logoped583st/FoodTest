package com.example.fruit_cusom_view.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruit_cusom_view.R
import com.example.fruit_cusom_view.databinding.BaseListConstraintBinding
import com.logoped583.fruit_tools.LoadingStateSealed
import kotlinx.android.synthetic.main.base_list_constraint.view.*

class BaseListConstraint : ConstraintLayout {

    var state: LoadingStateSealed<*, *>? = null
        set(value) {
            field = value
            mBinding.state = field
        }

    lateinit var mBinding: BaseListConstraintBinding

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.base_list_constraint,
            this,
            true
        )
        mBinding.viewGroupAnim = view_group
        val ta = context.obtainStyledAttributes(attrs, R.styleable.BaseListConstraint, 0, 0)
        val manager = ta.getInt(R.styleable.BaseListConstraint_layout_manager, 0)
        mBinding.rv.layoutManager = LayoutManager.values()[manager].getLayoutManager(context)
        mBinding.rv.verticalScrollbarPosition = right
        mBinding.rv.isVerticalScrollBarEnabled = true


        ta.recycle()
    }

    enum class LayoutManager {
        LINEAR {
            override fun getLayoutManager(context: Context): RecyclerView.LayoutManager =
                LinearLayoutManager(context)
        },
        GRID {
            override fun getLayoutManager(context: Context): RecyclerView.LayoutManager =
                GridLayoutManager(context, 0)
        };

        abstract fun getLayoutManager(context: Context): RecyclerView.LayoutManager
    }


}