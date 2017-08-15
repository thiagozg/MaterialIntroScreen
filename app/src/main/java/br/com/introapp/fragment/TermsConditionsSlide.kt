package br.com.introapp.fragment

import agency.tango.materialintroscreen.SlideFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.introapp.QuestionsActivity
import br.com.introapp.R
import br.com.introapp.data.SPInfo
import kotlinx.android.synthetic.main.fragment_terms_conditions_slide.*

/**
 * Created by thiagozg on 15/08/17.
 */
class TermsConditionsSlide : SlideFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_terms_conditions_slide, container, false)
    }

    /**
     * Pode continuar avancando..?
     */
    override fun canMoveFurther(): Boolean {
        if (cb_concordo.isChecked) {
            SPInfo(activity).updateIntroStatus(true)

            /*
             * essa chamada com o slide, cria mtas activitys na pilha, entao qdo
             * estiver chegado eh necessario dar um clear top na pilha
             *
             * {
             *  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
             *  activity.finish()
             * }
             */
            val intent = Intent(activity, QuestionsActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity.finish()
        }

        return cb_concordo.isChecked
    }

    /**
     * MSG de Erro caso ele nao ficar de acordo com os termos de uso
     */
    override fun cantMoveFurtherErrorMessage(): String {
        return activity.resources.getString(R.string.slide_4_checkbox_error)
    }

    override fun backgroundColor(): Int {
        return R.color.slide_4
    }

    override fun buttonsColor(): Int {
        return R.color.slide_button
    }

}