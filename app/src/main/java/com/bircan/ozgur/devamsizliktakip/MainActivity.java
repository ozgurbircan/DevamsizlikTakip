package com.bircan.ozgur.devamsizliktakip;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  static int DIALOG_HAKKINDA=1;
    private  static int DIALOG_DERS=2;
    private  static int DIALOG_DEVAMSIZLIK=3;
    ExpandableListView liste;
    private DatePicker picker;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    TextView dateView=null;
    Calendar calendar;
    int year, month, day;
    String datey;



    HashMap<String,List<String>> ulkeler;
    List<String> sehirler;
    ExpandableListViewData adapterSınıf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });



        ActionBar actionBar=getSupportActionBar();

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab));
        liste= (ExpandableListView) findViewById(R.id.expandableListView);
        picker = (DatePicker) findViewById(R.id.datePicker);
        ulkeler=bilgial();
        sehirler=new ArrayList<>(ulkeler.keySet());
        adapterSınıf=new ExpandableListViewData(this,ulkeler,sehirler);
        liste.setAdapter(adapterSınıf);


    }


    public static HashMap<String,List<String>> bilgial()
    {

        HashMap<String,List<String>> detay=new HashMap<>();
        List<String> ingilizce=new ArrayList<>();
        ingilizce.add("24.02.2015");
        ingilizce.add("27.02.2015");
        ingilizce.add("02.03.2015");
        List<String> ruby=new ArrayList<>();
        ruby.add("30.02.2015");
        ruby.add("07.03.2015");
        List<String> linux=new ArrayList<>();
        linux.add("08.03.2015");
        detay.put("Ruby",ruby);
        detay.put("İngilizce",ingilizce);
        detay.put("Linux",linux);

        return  detay;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.ekle:
                showDialog(DIALOG_DERS);
                return true;
            case R.id.devamsizlik_ekle:
                showDialog(DIALOG_DEVAMSIZLIK);
                return true;
            case R.id.hakkinda:
                showDialog(DIALOG_HAKKINDA);
                return true;
            case R.id.paylas:
                return true;
            default:return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog=null;
        if (id==DIALOG_HAKKINDA)
        {
            dialog=new Dialog(MainActivity.this);
            dialog.setTitle("Hakkında");
            dialog.setContentView(R.layout.hakkinda);
        }
        else if(id==DIALOG_DEVAMSIZLIK)
        {
            dialog=getDevamsizlikEkle();
        }
        else if(id==DIALOG_DERS)
        {

            dialog=getDersEkle();

        }
        else
        dialog=null;

        return dialog;
    }

    private Dialog getDersEkle() {
        LayoutInflater inflater =LayoutInflater.from(this);
        View layout=inflater.inflate(R.layout.ders_ekle,null);
        Button kaydet= (Button) layout.findViewById(R.id.ders_ekle_btn_kaydet);
        Button vazgec= (Button) layout.findViewById(R.id.ders_ekle_btn_vazgec);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Ders Ekle");
        builder.setView(layout);
        final AlertDialog dialog=builder.create();
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Ders Ekle");

                // Setting Dialog Message
                alertDialog.setMessage("Yeni Ders Eklemek İster Misiniz?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ab);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                // Showing Alert Message
                alertDialog.show();

            }
        });
        vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return dialog;
    }




    private Dialog getDevamsizlikEkle() {

        LayoutInflater inflater =LayoutInflater.from(this);
        View layout=inflater.inflate(R.layout.devamsizlik_ekle,null);
        Button kaydet= (Button) layout.findViewById(R.id.btn_kaydet);
        Button vazgec= (Button) layout.findViewById(R.id.btn_vazgec);
        final Spinner sp= (Spinner) layout.findViewById(R.id.spinner);
        final DatePicker datapicker= (DatePicker) layout.findViewById(R.id.datePicker);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Devamsizlık Ekle");
        builder.setView(layout);
        final AlertDialog dialog=builder.create();
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int gun=datapicker.getDayOfMonth();
                int ay=datapicker.getMonth()+1;
                int yil=datapicker.getYear();
                //dateView.setText(gun+"/"+ay+"/"+yil);
                String sgun= Integer.toString(gun);
                String say= Integer.toString(ay);
                String syil= Integer.toString(yil);
                String str=(sgun+"/"+say+"/"+syil);

                //String tarih= (String) dateView.getText();
                int pozisyon=sp.getSelectedItemPosition();
                String ders= (String) sp.getItemAtPosition(pozisyon);

                Ogrenci ogrenci=new Ogrenci(ders,str);
                Veritabani db=new Veritabani(getApplicationContext());
                long id=db.kayitEkle(ogrenci);
                if (id==-1)
                {
                    Toast.makeText(MainActivity.this,"kayıt sırasında bir hata oluştu",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"kayıt başarılı",Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }
        });
        vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return dialog;
    }




}

