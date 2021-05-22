package com.rightside.hackaton.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentLoginBinding
import com.rightside.hackaton.model.User
import com.rightside.hackaton.presenter.LoginPresenter
import com.rightside.hackaton.view.contracts.LoginContract
import org.koin.android.ext.android.inject


class LoginFragment : Fragment(R.layout.fragment_login), LoginContract.View {

    lateinit var binding : FragmentLoginBinding
    private var mAuth: FirebaseAuth? = null

    override val presenter: LoginPresenter by inject()
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        googleSignInConfig()
        mAuth = FirebaseAuth.getInstance();
        displaySignIn()
    }

    private fun displaySignIn() {
        val signIntent : Intent = mGoogleSignInClient.signInIntent
        resultContract.launch(signIntent)
    }
    private fun googleSignInConfig() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    private val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            account?.let { authWithGoogle(account) }
        }
    }

    private fun authWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener {
            if (it.isSuccessful) {
                verifyUser(account)
            }
        }
    }

    private fun verifyUser(account: GoogleSignInAccount) {
        val user = mAuth?.currentUser
        val documentReference: DocumentReference? =
            user?.uid?.let {
                FirebaseFirestore.getInstance().collection(
                    "users"
                ).document(it)
            }
        documentReference?.get()?.addOnCompleteListener { task: Task<DocumentSnapshot?> ->
            if (task.isSuccessful) {
                val documentSnapshot = task.result
                if (documentSnapshot!!.exists()) {
                    presenter.saveUserId(
                        User(
                            account.displayName.toString(),
                            account.email.toString(),
                            account.photoUrl.toString(),
                            user.uid
                        )
                    )
                    finishFragment()
                } else {
                    val user = User(
                        account.displayName.toString(),
                        account.email.toString(),
                        account.photoUrl.toString(),
                        user.uid
                    )
                    FirebaseFirestore.getInstance().collection("users").document(user.uuid).set(user)
                            .addOnCompleteListener {
                                if (task.isSuccessful) {
                                    presenter.saveUserId(user)
                                    finishFragment()
                                }
                            }
                }
            }
        }
    }

    override fun finishFragment() {
        findNavController().popBackStack()
    }

}