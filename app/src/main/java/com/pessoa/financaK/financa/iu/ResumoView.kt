package com.pessoa.financaK.financa.iu

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.pessoa.financaK.R
import com.pessoa.financaK.financa.extension.formataParaBrasileiro
import com.pessoa.financaK.financa.model.Resumo
import com.pessoa.financaK.financa.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualizar() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceita = resumo.receita

        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }


    }


    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa

        with(view.resumo_card_despesa)
        {
            setTextColor(corDespesa)
            view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()


        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPor(total)

        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }

    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO) {
            return corReceita
        } else {
            return corDespesa

        }
    }
}