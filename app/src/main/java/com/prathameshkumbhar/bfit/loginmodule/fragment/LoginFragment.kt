package com.prathameshkumbhar.bfit.loginmodule.fragment

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.droidman.ktoasty.KToasty
import com.droidman.ktoasty.showErrorToast
import com.droidman.ktoasty.showSuccessToast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.coremodule.InputFilters
import com.prathameshkumbhar.bfit.databinding.FragmentLoginBinding
import com.prathameshkumbhar.bfit.onboardingmodule.activity.OnboardActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start
import timber.log.Timber

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLoginClient : GoogleSignInClient
    private val navController by lazy {
        findNavController()
    }
    private val args : LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleLoginClient = GoogleSignIn.getClient(context!!,gso)

        binding.loginEmailEditText.setText(args.email)
        binding.loginPassEditText.setText(args.password)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




            binding.loginEmailEditText.filters = arrayOf(InputFilters.emailFilter)

            //Sign up button
            binding.notRegisteredSignUpTextView.setOnClickListener {

                if(checkForInternet(requireContext())) {
                    KToasty.success(requireContext(), "Connected to Internet !").show()
                    //Go to signUp Fragment
                    navController.navigate(
                        LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
                    )
                }else{
                    KToasty.error(requireContext(),"Not connected to Internet. Please connect to internet service to proceed further !").show()
                }
            }

            binding.signInButton.setOnClickListener {

                val email = binding.loginEmailEditText.text.toString().trim()
                val pass = binding.loginPassEditText.text.toString().trim()
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

                if(checkForInternet(requireContext())){
                    KToasty.success(requireContext(),"Connected to Internet !").show()

                    if (email.isNotEmpty() && pass.isNotEmpty()) {
                        if (email.matches(emailPattern.toRegex())){
                            if(pass.length >= 8) {
                                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        requireContext().showSuccessToast("Logged In Successfully!")
                                        lifecycleScope.launch {
                                            binding.progressBar.visibility = View.VISIBLE
                                            disableAllViews()
                                            delay(4000)
                                            //go to OnboardActivity
                                            start<OnboardActivity>(){

                                                val sharePrefLogin : SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
                                                var editor : SharedPreferences.Editor = sharePrefLogin.edit()
                                                editor.putBoolean("flag",true)
                                                editor.apply()

                                                activity?.finish()
                                            }
                                        }
                                    } else {
                                        KToasty.info(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }else{
                                KToasty.info(requireContext(),"Password must not be less than 8 Characters !",Toast.LENGTH_SHORT).show()
                            }

                        }else{
                            KToasty.warning(requireContext(), "Invalid Email!", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        KToasty.warning(requireContext(), "Empty fields are not allowed !", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    KToasty.error(requireContext(),"Not connected to Internet. Please connect to internet service to proceed further !").show()
                }
            }

            binding.signInForgotPassTextView.setOnClickListener {

                if(checkForInternet(requireContext())){
                    KToasty.success(requireContext(),"Connected to Internet !").show()
                    val email = binding.loginEmailEditText.text.toString().trim()
                    //Go to forgot pass fragment
                    navController.navigate(
                        LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(email)
                    )
                }else{
                    KToasty.error(requireContext(),"Not connected to Internet. Please connect to internet service to proceed further !").show()
                }

            }

            binding.googleLogin.setOnClickListener {

                if(checkForInternet(requireContext())){
                    KToasty.success(requireContext(),"Connected to Internet !").show()
                    signInGoogle()
                    Timber.e(TAG,"Inside the onset listener of google auth")
                }else{
                    KToasty.error(requireContext(),"Not connected to Internet. Please connect to internet service to proceed further !").show()
                }

            }
    }

    //Google authentication starts from here
    private fun signInGoogle(){
        val signInClient = googleLoginClient.signInIntent
        launcher.launch(signInClient)
        Timber.e(TAG,"in the sign in method")
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            activityResult -> if (activityResult.resultCode == Activity.RESULT_OK){
        val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
        handleActivityResult(task)
        Timber.e(TAG,"in the launcher expression")
    }
    }

    private fun handleActivityResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if(account != null){
                updateUI(account)
                Timber.e(TAG,"in the handle activity result method")
            }
        }else{
            requireContext().showErrorToast(task.exception.toString())
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {

            if (it.isSuccessful){
                Timber.e(TAG,"in the Update Ui method and inside the it successful if block")
                requireContext().showSuccessToast("Logged In Successfully!")
                lifecycleScope.launch(){
                    binding.progressBar.visibility = View.VISIBLE
                    disableAllViews()
                    delay(4000)
                    start<OnboardActivity>(){
                        val sharePrefLogin : SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
                        var editor : SharedPreferences.Editor = sharePrefLogin.edit()
                        editor.putBoolean("flag",true)
                        editor.apply()

                        activity?.finish()
                    }
                }
            }else{
                requireContext().showErrorToast(it.exception.toString())
            }
        }
    }

    private fun disableAllViews(){
        binding.scrollView3.visibility = View.GONE
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}