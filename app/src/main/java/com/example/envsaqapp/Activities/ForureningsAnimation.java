package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.internal.http.multipart.Part;
import com.example.envsaqapp.R;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class ForureningsAnimation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private static double userX;
    private static double userY;
    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;
    private Integer item7ID;
    private Integer item8ID;
    private WebView webview;
    private ImageView imageView;
    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forurenings_animation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //webview = findViewById(R.id.ForAniWebView);
        imageView = findViewById(R.id.ForAniImageView);
        imageView.bringToFront();
        mDrawerLayout = findViewById(R.id.ForAniDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

        Intent intent = getIntent();
        userX = intent.getDoubleExtra("userX", userX);
        userY = intent.getDoubleExtra("userY", userY);
/*
        String url = "https://envs.au.dk/faglige-omraader/luftforurening-udledninger-og-effekter/data-om-luftkvalitet/luftudsigten/";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setColorScheme(CustomTabsIntent.COLOR_SCHEME_LIGHT);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
        */
        //LoadWebView();
        //LoadImage loadImage = new LoadImage(imageView);
        //loadImage.execute(uri + "");

        getImages();
        //testingTarget();
    }
//File f = new File("http://www2.dmu.dk/thorben_new/Danmark/noxbum_" + 54 + ".png");
/*
private void testingFileImport() {
    Part file;

// more code
    try {
        InputStream is = file.getInputStream();

        File f = new File("http://www2.dmu.dk/thorben_new/Danmark/noxbum_" + 54 + ".png");

        OutputStream os = new FileOutputStream(f);
        byte[] buf = new byte[1024];
        int len;

        while ((len = is.read(buf)) > 0) {
            os.write(buf, 0, len);
        }

        os.close();
        is.close();

    } catch (IOException e) {
        System.out.println("Error");
    }
}*/

    ArrayList<Bitmap> arrayList = new ArrayList();
    //String uri = "http://www2.dmu.dk/thorben_new/Danmark/noxbum_" + i + ".png";

    /*private void testingTarget() {

        Picasso.get().load(uri).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Toast.makeText(ForureningsAnimation.this, "Yehaaw", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {

            }
        });
        i++;

        //Log.d("SHOWARRAYLIST", arrayList.size() + "");

    }*/

    Bitmap bitmap1;

    private void getImages() {
        String uris = "http://www2.dmu.dk/thorben_new/Danmark/noxbum_" + 1 + ".png";

        while (i < 6) {
            String uri = "http://www2.dmu.dk/thorben_new/Danmark/noxbum_" + i + ".png";
            Picasso.get().load(uri).into(target, new Callback() {
                @Override
                public void onSuccess() {
                    
                }

                @Override
                public void onError(Exception e) {

                }
            });
            i++;

            Log.d("YES", i + "");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }




//RequestCreator image = Picasso.get().load(uri);
//imageView.setImageURI(new Uri(uri));
            /*Picasso.get().load(uri).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    i++;

                    getImages();
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(ForureningsAnimation.this, "something went wrong", Toast.LENGTH_LONG).show();

                }
            });
        }
    }*/

    private void showImageAsync() {

    }

    private void LoadWebView() {
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://envs.au.dk/videnudveksling/luft/luftudsigten/oversigtskort");
        //http://lpdv.spatialsuite.dk/spatialmap
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    ArrayList<Image> images = new ArrayList<>();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ForHerItem1:
                item1ID = item.getItemId();
                ChangeActivity(item1ID);
                return true;
            case R.id.KortItem2:
                item2ID = item.getItemId();
                ChangeActivity(item2ID);
                return true;

            case R.id.UdsigtItem3:
                item3ID = item.getItemId();
                ChangeActivity(item3ID);
                return true;/*
            case R.id.GroenItem4:
                item4ID = item.getItemId();
                ChangeActivity(item4ID);
                return true;*/
            case R.id.NotiItem5:
                item5ID = item.getItemId();
                //Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
                ChangeActivity(item5ID);
                return true;
            case R.id.SkalaItem6:
                item6ID = item.getItemId();
                //Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
                ChangeActivity(item6ID);
                return true;
            case R.id.infoItem7:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item7ID = item.getItemId();
                ChangeActivity(item7ID);
                return true;
            case R.id.KortItem8:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item8ID = item.getItemId();
                ChangeActivity(item8ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void ChangeActivity(Integer ID) {
        if (ID == item1ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, ForureningHer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        } else if (ID == item2ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, MainActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item3ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, NavigationUdsigt.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item4ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    //startActivity(i);
                    Toast.makeText(ForureningsAnimation.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);

        } else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, Notifikationer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        } else if (ID == item6ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, Forureningskala.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        } else if (ID == item7ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, Info.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item8ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsAnimation.this, webViewActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.ForAniNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

   /* private class LoadImage extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView ivResult){
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Integer i = 1;
            //while (i < 74) {
                //uri = "http://www2.dmu.dk/thorben_new/Danmark/noxbum_" + i +".png";
                i++;
                Log.d("DOINBACKGROUND1", "" + uri);



            /*HttpGet httpRequest = null;

            try {
                httpRequest = new HttpGet(bitmapUrl.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            URLConnection httpclient = new URLConnection() {
                @Override
                public void connect() throws IOException {

                }
            };
            HttpResponse response = (HttpResponse) httpclient.execute
                    (httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity
                    (entity);
            InputStream instream = bufHttpEntity.getContent();
            bm = BitmapFactory.decodeStream(instream);*/
                /*try {
                    //InputStream is = new java.net.URL(uri).openStream();
                    //Log.e("DOINBACKGROUND1", is.toString());
                    //bitmap = BitmapFactory.decodeStream(uri.openConnection().getInputStream());


                    //Log.e("DOINBACKGROUND1", "Jeg var her" + bitmap.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("DOINBACKGROUND1", e.toString());
                }

            return bitmap;
            }
            //Toast.makeText(ForureningsAnimation.this,"DO IT AGAIN",Toast.LENGTH_LONG);

        //}

        @Override
        protected void onPostExecute(Bitmap bitmap) {
               // imageView.postInvalidateDelayed(2000);
            try {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    Log.d("DOINBACKGROUND1", "ONPOSTEXECUTE");
                } else { Log.e("DOINBACKGROUND1", "bitmap = null");
                }
            } catch (Exception e) {
                Log.e("DOINBACKGROUND1", e.getMessage());
            }

        }
    }*/
}