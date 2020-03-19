package com.pessoa.financaK.financa.iu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.pessoa.financaK.R
import com.pessoa.financaK.financa.extension.formataParaBrasileiro
import com.pessoa.financaK.financa.extension.formataBrasileiro
import com.pessoa.financaK.financa.extension.limetaEmAte
import com.pessoa.financaK.financa.model.Tipo
import com.pessoa.financaK.financa.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacaoAdapter(
    private val transacao: List<Transacao>,
    private val context: Context
) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada =
            LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacao[position]

        adicionarValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)
        adicionaCategoria(transacao, viewCriada)
        adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaData(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_data.text = transacao.data.formataBrasileiro()
    }

    private fun adicionaCategoria(
        transacao: Transacao,
        viewCriada: View
    ) {
        var categoriaFormatada = transacao.categoria
        if (categoriaFormatada.length > limiteDaCategoria) {
            categoriaFormatada = " ${categoriaFormatada.substring(0, limiteDaCategoria)}..."
        }


        viewCriada.transacao_categoria.text = categoriaFormatada.limetaEmAte(limiteDaCategoria)
    }

    private fun adicionaIcone(
        transacao: Transacao,
        viewCriada: View
    ) {
        val icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita

        }
        return R.drawable.icone_transacao_item_despesa
    }


    private fun adicionarValor(
        transacao: Transacao,
        viewCriada: View
    ) {
        val cor: Int = corPor(transacao.tipo)

        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(
                context, R.color.receita
            )
        }
        return ContextCompat.getColor(
            context, R.color.despesa

        )
    }


    override fun getItem(position: Int): Transacao {
        return transacao[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getCount(): Int {
        return transacao.size
    }


}