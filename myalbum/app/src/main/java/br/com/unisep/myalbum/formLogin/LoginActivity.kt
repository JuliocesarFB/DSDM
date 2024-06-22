package br.com.unisep.myalbum.formLogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.unisep.myalbum.databinding.ActivityLoginBinding
import br.com.unisep.myalbum.ListAlbumActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView(){
        binding.btnLogin.setOnClickListener{ view ->
            val email = binding.etUser.text.toString()
            val password = binding.etPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else{
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ authentication ->
                        if (authentication.isSuccessful){
                            goToListAlbum()
                        }
                    }.addOnFailureListener{
                        val snackbar = Snackbar.make(view, "Erro ao fazer o login do usuário", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }
            }

        }
   }

   private fun goToListAlbum(){
        val intentToListAlbum = Intent(this, ListAlbumActivity::class.java)
        startActivity(intentToListAlbum)
    }



}