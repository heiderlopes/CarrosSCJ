package com.example.logonpf.carros.ui.novocarro


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.logonpf.carros.R
import com.example.logonpf.carros.api.CarroAPI
import com.example.logonpf.carros.api.RetrofitClient
import com.example.logonpf.carros.model.Carro
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovoCarroFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_novo_carro, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSalvar.setOnClickListener {
            val api = RetrofitClient
                    .getInstance()
                    .create(CarroAPI::class.java)
            val carro = Carro(null,
                    inputMarca.editText?.text.toString(),
                    inputModelo.editText?.text.toString(),
                    inputAno.editText?.text.toString().toInt(),
                    inputPlaca.editText?.text.toString(),
                    "")

            api.salvar(carro)
                    .enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("CARRO", t?.message)
                        }

                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true) {
                                Toast.makeText(context,
                                        "Sucesso",
                                        Toast.LENGTH_SHORT).show()
                                limparCampos()
                            } else {
                                Toast.makeText(context,
                                        "Errou coloque aqui o Faustão",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }
    }

    private fun limparCampos() {
        inputMarca.editText?.setText("")
        inputModelo.editText?.setText("")
        inputAno.editText?.setText("")
        inputPlaca.editText?.setText("")
    }

}// Required empty public constructor
