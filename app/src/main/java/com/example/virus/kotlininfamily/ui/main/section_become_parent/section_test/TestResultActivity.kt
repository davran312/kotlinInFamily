package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test


import android.content.Intent
import android.os.Bundle
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.TestQuestion
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.BecomeParentActivity
import com.example.virus.kotlininfamily.utils.Const.ONE_CORRECT_QUESTION_IN_PERCENTAGE
import kotlinx.android.synthetic.main.activity_test_result.*
import kotlin.math.roundToInt


class TestResultActivity : BaseActivity() {
    private var correctAnswers: Int = 0
    private var percentageOfCorrectAnswers: Int = 0

    private var indexesYesAnswersAreCorrect: Array<out String>? = null
    private var indexesNoAnswersAreCorrect: Array<out String>? = null
    private var hashMap: HashMap<Int, TestQuestion>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_result)

        getCorrectAnswersInPercentage()
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")
        setResult()
        setProgressOnBar()
        setOnSubmitButtonClickListener()

    }

    private fun setResult() {

        if (percentageOfCorrectAnswers == 100) {
            result_text_view.text = getString(R.string.excellent_result)
        } else if (percentageOfCorrectAnswers <= 100 && percentageOfCorrectAnswers >= 70) {
            result_text_view.text =  getString(R.string.normal_result, percentageOfCorrectAnswers.toString())
        } else if (percentageOfCorrectAnswers < 70) {
            result_text_view.textSize = 14F
            result_text_view.text =  getString(R.string.bad_result, percentageOfCorrectAnswers.toString())
        }
    }

    private fun setProgressOnBar() {
        progressBar.progress = percentageOfCorrectAnswers
        txtProgress.text = percentageOfCorrectAnswers.toString() + "%"
    }

    private fun getCorrectAnswersInPercentage() {

        initRightAnswersAndMap()
        countCorrectAnswers()
        convertToPercentage()

    }

    private fun countCorrectAnswers() {
        for (i in 0 until indexesYesAnswersAreCorrect!!.size) {
            if (hashMap!![indexesYesAnswersAreCorrect!![i].toInt() - 1]!!.isResult!!) {
                correctAnswers++
            }
        }

        for (i in 0 until indexesNoAnswersAreCorrect!!.size) {
            if (!hashMap!![indexesNoAnswersAreCorrect!![i].toInt() - 1]!!.isResult!!) {
                correctAnswers++
            }
        }
    }

    private fun convertToPercentage() {

        percentageOfCorrectAnswers = (ONE_CORRECT_QUESTION_IN_PERCENTAGE * correctAnswers).roundToInt()

    }

    private fun initRightAnswersAndMap() {

        indexesYesAnswersAreCorrect = resources.getStringArray(R.array.yesAnswerIndexesAreRight)
        indexesNoAnswersAreCorrect = resources.getStringArray(R.array.noAnswerIndexesAreRight)
        hashMap = intent.getSerializableExtra("map") as HashMap<Int, TestQuestion>

    }

    private fun setOnSubmitButtonClickListener(){
        btn_submit_result.setOnClickListener {
            finish()


        }

    }
}