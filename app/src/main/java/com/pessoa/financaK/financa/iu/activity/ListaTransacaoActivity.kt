package com.pessoa.financaK.financa.iu.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.pessoa.financaK.R
import com.pessoa.financaK.financa.dao.TransacaoDAO
import com.pessoa.financaK.financa.iu.ResumoView
import com.pessoa.financaK.financa.iu.adapter.ListaTransacaoAdapter
import com.pessoa.financaK.financa.iu.dialog.AdicionaTransacaoDialog
import com.pessoa.financaK.financa.iu.dialog.AlteraTransacaoDialog
import com.pessoa.financaK.financa.model.Tipo
import com.pessoa.financaK.financa.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*


class ListaTransacaoActivity : AppCompatActivity() {
    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes
    private val viewDaActivity by lazy {
        window.decorView
    }
    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogoDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogoDeAdicao(Tipo.DESPESA)
        }
    }


    private fun chamaDialogoDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .chama(tipo) { transacaoCriada ->
                adiciona(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            }
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewGroupDaActivity, transacoes)
        resumoView.atualizar()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacaoAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracao(transacao, position)
            }
                setOnCreateContextMenuListener { menu, _, _ ->
                    menu.add(Menu.NONE,1 , Menu.NONE,"Remover")
                }
        }

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val idDoMenu = item?.itemId
        if (idDoMenu == 1){
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDaTransacao = adapterMenuInfo.position
            remover(posicaoDaTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remover(posicao: Int) {
        dao.remover(posicao)
        atualizaTransacoes()
    }

    private fun chamaDialogDeAlteracao(
        transacao: Transacao,
        position: Int
    ) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
            .chama(transacao) {transacaoAlterada ->
                altera(transacaoAlterada, position)

            }
    }

    private fun altera(transacao: Transacao, position: Int) {
        altera(transacao, position)
        atualizaTransacoes()
    }
}



