package br.com.introapp

import agency.tango.materialintroscreen.MaterialIntroActivity
import agency.tango.materialintroscreen.MessageButtonBehaviour
import agency.tango.materialintroscreen.SlideFragmentBuilder
import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.introapp.data.SPInfo
import br.com.introapp.fragment.TermsConditionsSlide

/**
 * Created by thiagozg on 14/08/17.
 */
class IntroActivity : MaterialIntroActivity() {

    /**
     ** Quando utilizar uma API e ela nao tiver opcao para sobescrever arquivos de texto (ficam em outra linguagem)
     ** sobescrever em 'strings.xml' as chaves q sao utilizadas pela API, por exemplo como foi feito:
     *
     *  <string name="grant_permissions">Liberar permissões</string>
     *  <string name="mis_grant_permissions">Liberar permissões</string>
     *  <string name="please_grant_permissions">
     *  Para poder prosseguir é importante liberar as permissões de localização
     *  </string>
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verifyIntroActivity()

        /**
         * Algumas opcoes do q colocar nos slides...
         */
        hideBackButton()
//        setSkipButtonVisible()
//        enableLastSlideAlphaExitTransition(true)

        /****************/

        // internamente esta sendo utilizado ViewPager
        addSlide(
                SlideFragmentBuilder()
                        .backgroundColor(R.color.slide_1)
                        .buttonsColor(R.color.slide_button)
                        .title(resources.getString(R.string.slide_1_title))
                        .description(resources.getString(R.string.slide_1_description))
                        .image(R.drawable.slide_1)
                        .build(),
                MessageButtonBehaviour(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        // SnackBar (msg q aparece no rodape da tela)
                        showMessage(resources.getString(R.string.slide_1_button_message))
                    }
                }, resources.getString(R.string.slide_1_button_label))

        )

        // adicionando solicitacao de permissao no slide
        val neededPermissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        )
        addSlide(
                SlideFragmentBuilder()
                        .backgroundColor(R.color.slide_2)
                        .buttonsColor(R.color.slide_button)
                        .title(resources.getString(R.string.slide_2_title))
                        .description(resources.getString(R.string.slide_2_description))
                        .image(R.drawable.slide_2)
                        .neededPermissions(neededPermissions) // <-- OBRIGATORIO
                        .build()
        )


        val possiblePermissions = arrayOf(Manifest.permission.CAMERA)
        addSlide(
                SlideFragmentBuilder()
                        .backgroundColor(R.color.slide_3)
                        .buttonsColor(R.color.slide_button)
                        .title(resources.getString(R.string.slide_3_title))
                        .description(resources.getString(R.string.slide_3_description))
                        .image(R.drawable.slide_3)
                        .neededPermissions(possiblePermissions) // <-- NAO EH OBRIGATORIO
                        .build()
        )

        // Custom Slide
        addSlide(TermsConditionsSlide())
    }

    private fun verifyIntroActivity() {
        if (SPInfo(this).isIntroActivityShown()) {
            startActivity(Intent(this, QuestionsActivity::class.java))
            finish()
        }
    }

}