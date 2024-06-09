package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnFollow;
    private TextView helloworld, description;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        // Initialize views
        helloworld = findViewById(R.id.helloworld);
        description = findViewById(R.id.description);
        btnFollow = findViewById(R.id.btnFollow);

        // Get user information from intent
        Intent intent = getIntent();
        if (intent != null) {
            currentUser = (User) intent.getSerializableExtra("user");
            if (currentUser != null) {
                // Set user information
                helloworld.setText(currentUser.getName());
                description.setText(currentUser.getDescription());
                // Set initial follow state
                btnFollow.setText(currentUser.followed ? "UNFOLLOW" : "FOLLOW");

                btnFollow.setOnClickListener(v -> {
                    currentUser.followed = !currentUser.followed;
                    // Update the text of the button
                    btnFollow.setText(currentUser.followed ? "UNFOLLOW" : "FOLLOW");

                });
            }
        }
        //Reading the random number
        Intent receivingEnd = getIntent();
        String name = receivingEnd.getStringExtra("name");
        String description = receivingEnd.getStringExtra("description");
        String followed = receivingEnd.getStringExtra("followed");
        String id = receivingEnd.getStringExtra("id");

        User user = dbHandler.getUser(name);
    }

}