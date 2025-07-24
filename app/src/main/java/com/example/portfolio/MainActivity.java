package com.example.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    HorizontalScrollView horizontalScrollView;
    LinearLayout buttonContainer;
    Handler handler = new Handler();
    int scrollX = 0;
    int scrollAmount = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn=findViewById(R.id.btnSkill);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v)
           {
               Intent intent=new Intent(MainActivity.this,Skils.class);
               startActivity(intent);
           }
        });

        Button btn2=findViewById(R.id.btnEducation);

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, education.class);
                startActivity(intent);
            }
        });

        Button btn3=findViewById(R.id.btnProjects);

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, Project.class);
                startActivity(intent);
            }
        });

        Button btn4=findViewById(R.id.btnExperience);

        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, Experience.class);
                startActivity(intent);
            }
        });

        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        buttonContainer = findViewById(R.id.buttonContainer);

        // Start auto-scrolling
        startAutoScroll();

        Button leetcodeButton = findViewById(R.id.leetcodeButton);
        // Example of a button click.  Replace with your actual links
        leetcodeButton.setOnClickListener(v -> openLink("https://leetcode.com/u/dhruvpatil_/"));

        findViewById(R.id.linkedinButton).setOnClickListener(v -> openLink("https://www.linkedin.com/in/dhruvpatil1606/"));

        findViewById(R.id.githubButton).setOnClickListener(v -> openLink("https://github.com/dhruvpatil1606"));

        findViewById(R.id.YouTubeButton).setOnClickListener(v -> openLink("https://www.youtube.com/@D-trino"));

        findViewById(R.id.xButton).setOnClickListener(v -> openLink("https://x.com/dhruvpatil___"));

        findViewById(R.id.instagramButton).setOnClickListener(v -> openLink("https://www.instagram.com/dhruvpatil___/?hl=en"));

    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private int scrollDirection = 1; // 1 for left-to-right, -1 for right-to-left

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollX += scrollAmount * scrollDirection; // Multiply by direction

                horizontalScrollView.smoothScrollTo(scrollX, 0);

                int maxScrollX = buttonContainer.getWidth() - horizontalScrollView.getWidth();

                if (scrollDirection == 1) { // Left-to-right
                    if (scrollX >= maxScrollX) {
                        scrollDirection = -1; // Switch to right-to-left
                        scrollX = maxScrollX; // Ensure we are exactly at the end
                    }
                } else { // Right-to-left
                    if (scrollX <= 0) {
                        scrollDirection = 1; // Switch to left-to-right
                        scrollX = 0;       // Ensure we are exactly at the start
                    }
                }

                handler.postDelayed(this, 30);
            }
        }, 30);
    }
}