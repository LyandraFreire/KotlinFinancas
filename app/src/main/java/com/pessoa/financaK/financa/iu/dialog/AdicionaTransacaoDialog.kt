package com.pessoa.financaK.financa.iu.dialog

import android.content.Context
import android.view.ViewGroup
import com.pessoa.financaK.R
import com.pessoa.financaK.financa.iu.activity.ListaTransacaoActivity
import com.pessoa.financaK.financa.model.Tipo

class AdicionaTransacaoDialog(
    viewGroup: ViewGroup,
    context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }
}