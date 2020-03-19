package com.pessoa.financaK.financa.iu.dialog

import android.content.Context
import android.view.ViewGroup
import com.pessoa.financaK.R
import com.pessoa.financaK.financa.extension.formataBrasileiro
import com.pessoa.financaK.financa.model.Tipo
import com.pessoa.financaK.financa.model.Transacao

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    fun chama(transasao: Transacao, delegate: (transacao : Transacao ) -> Unit) {
        val tipo = transasao.tipo
        super.chama(tipo,delegate)
        inicializaCampos(transasao)
    }

    private fun inicializaCampos(transasao: Transacao) {
        val tipo = transasao.tipo
        inicializaCampoValor(transasao)
        inicializarCampoData(transasao)
        inicializaCampoCategoria(tipo, transasao)
    }

    private fun inicializaCampoCategoria(
        tipo: Tipo,
        transasao: Transacao
    ) {
        val categoriasRetornadas = context.resources.getStringArray(categoriasPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transasao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializarCampoData(transasao: Transacao) {
        campoData.setText(transasao.data.formataBrasileiro())
    }

    private fun inicializaCampoValor(transasao: Transacao) {
        campoValor.setText(transasao.valor.toString())
    }


}