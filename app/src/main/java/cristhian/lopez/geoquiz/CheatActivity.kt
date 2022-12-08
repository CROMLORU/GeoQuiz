package cristhian.lopez.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cristhian.lopez.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOW = "cristhian.lopez.geoquiz.answer_show"
private const val EXTRA_ANSWER_IS_TRUE =
    "cristhian.lopez.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShowResult(true)
        }
    }
    private fun setAnswerShowResult(isAnswerShow: Boolean) {
        val data = intent.apply {
            putExtra(EXTRA_ANSWER_SHOW, isAnswerShow)
        }
        setResult(Activity.RESULT_OK, data)
    }
    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent(packageContext, CheatActivity::class.java).apply{
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}