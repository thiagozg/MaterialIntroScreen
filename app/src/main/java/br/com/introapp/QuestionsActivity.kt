package br.com.introapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import br.com.introapp.adapter.QuestionsAdapter
import br.com.introapp.data.Mock
import br.com.introapp.domain.Question
import kotlinx.android.synthetic.main.activity_questions.*

/**
 * Created by thiagozg on 14/08/17.
 */
class QuestionsActivity : AppCompatActivity() {

    val questions = ArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questions.addAll( Mock.generateQuestionList() )
        initRecycler()
    }

    private fun initRecycler() {
        rv_questions.setHasFixedSize(true)

        val mLayoutManager = LinearLayoutManager(this)
        rv_questions.layoutManager = mLayoutManager

        val divider = DividerItemDecoration( this, mLayoutManager.orientation)
        rv_questions.addItemDecoration(divider)

        val adapter = QuestionsAdapter(this, questions)
        rv_questions.adapter = adapter
    }
}