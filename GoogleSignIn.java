private void getUserInfo() {

    GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
    if (acct != null) {
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri uri = acct.getPhotoUrl();

        userName.setText(personName);
        userGivenName.setText(personGivenName);
        userFamilyName.setText(personFamilyName);
        userEmail.setText(personEmail);
        userId.setText(personId);

        Picasso.get().load(uri).into(userPhoto);

    }

}

implementation 'com.google.firebase:firebase-auth:16.1.0'
implementation 'com.google.android.gms:play-services-auth:16.0.1'

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;

    private GoogleSignInClient mGoogleSignInClient;

    SignInButton signInButton;

    Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init();

        setOnClick();

    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        updateUI(currentUser);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);

                assert account != null;

                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {

                updateUI(null);

            }

        }
        
    }

    private void setOnClick() {

        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                signIn();

            }

        });

        signOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                signOut();

            }

        });

    }

    private void init() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        signInButton = findViewById(R.id.signInButton);

        signOutButton = findViewById(R.id.signOutButton);

    }
    
    /*
    
    Four Methods
    
    1. updateUI();
    2. firebaseAuthWithGoogle();
    3. signIn();
    4. signOut();
    
     */

    private void updateUI(FirebaseUser user) {

        if (user != null) {

            signInButton.setVisibility(View.GONE);

            signOutButton.setVisibility(View.VISIBLE);

        } else {

            signInButton.setVisibility(View.VISIBLE);

            signOutButton.setVisibility(View.GONE);

        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(user);

                        } else {

                            updateUI(null);

                        }
                    }
                });
    }

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void signOut() {

        mAuth.signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        updateUI(null);

                    }

                });

    }

}
