package com.example.admin.formulario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String loggin, mail,passw,passw2, sexo, hobbies = "", departamento,ciudad, fecha="";
    private EditText eloggin,email, pass, pass2,edate;
    private RadioButton rmale, rfemale;
    private CheckBox cleer, cajedrez, cyoga, cnadar;
    private Spinner sCiudades, sDepartamentos;
    private TextView tResumen;
    private DatePickerDialog calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eloggin = (EditText) findViewById(R.id.eloggin);
        pass = (EditText) findViewById(R.id.epass);
        pass2 = (EditText) findViewById(R.id.epass2);
       email = (EditText) findViewById(R.id.eMail);
        rmale= (RadioButton) findViewById(R.id.rmale);
        rfemale= (RadioButton) findViewById(R.id.rfemale);
        cleer= (CheckBox) findViewById(R.id.cleer);
        cajedrez= (CheckBox) findViewById(R.id.cajedrez);
        cyoga= (CheckBox) findViewById(R.id.cyoga);
        cnadar= (CheckBox) findViewById(R.id.cnadar);
        tResumen = (TextView) findViewById(R.id.tresumen);
        edate = (EditText) findViewById(R.id.edate);
        final Calendar fechaactual = Calendar.getInstance();
        this.sDepartamentos = (Spinner) findViewById(R.id.sDepartamentos);
        this.sCiudades = (Spinner) findViewById(R.id.sCiudades);
        CargarSpinner();
        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  calendario = new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
                       public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                                edate.setText(dia + "/"+ (mes + 1) + "/" + ano);
                                fecha=String.valueOf(dia)+"/"+String.valueOf(mes+1)+"/"+String.valueOf(ano);
                            }},fechaactual.get(Calendar.YEAR),fechaactual.get(Calendar.MONTH),fechaactual.get(Calendar.DAY_OF_MONTH));
                          calendario.show();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {


        departamento = parent.getItemAtPosition(pos).toString();
        int ciudades[] = {R.array.array_amazonas,
                R.array.array_antioquia, R.array.array_arauca,R.array.array_atlantico,R.array.array_bolivar,R.array.array_boyaca,R.array.array_caldas,R.array.array_caqueta,R.array.array_casanare,R.array.array_cauca,R.array.array_cesar,R.array.array_choco,
                R.array.array_cordoba,R.array.array_cundinamarca,R.array.array_distrito,R.array.array_guajira,
                R.array.array_guania, R.array.array_guaviare, R.array.array_huila,
                R.array.array_magdalena, R.array.array_meta, R.array.array_narino,
                R.array.array_nsantander,R.array.array_putumayo, R.array.array_quindio,
                R.array.array_risaralda, R.array.array_san, R.array.array_santander,
                R.array.array_sucre, R.array.array_tolima, R.array.array_vcauca,
                R.array.array_vaupes, R.array.array_vichada};

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                ciudades[pos],
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    private void CargarSpinner() {


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sDepartamentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sDepartamentos.setAdapter(adapter);
        this.sDepartamentos.setOnItemSelectedListener(this);
    }

    public void aceptar(View view) {
        int cont= verificar();

        if(cont==0){

       tResumen.setText("Resumen de registro: \n" + "User: "+loggin + " \nEmail: " + mail + " \nSexo: " + sexo + " \nFecha de Nacimiento: " +fecha+ " \nCiudad de Nacimiento: " + departamento+ ", " +ciudad+" \nHobbies: " + hobbies);
      }

      if(cont==1){
          tResumen.setText("Todos los campos son obligatorios.");
      }
      if(cont==2){
          tResumen.setText("Las contraseñas no coinciden.");
      }



    }

    public int verificar(){
        int cont=0;
        if(eloggin.getText().toString().isEmpty()){
            cont=1;
        }
        else{
            loggin=eloggin.getText().toString();
        }

        if(email.getText().toString().isEmpty()){
            cont=1;
        }
        else {
            mail=email.getText().toString();
        }

        hobbies="";
        if (cleer.isChecked()) {
            hobbies += "Leer ";
        }
        if (cajedrez.isChecked()) {
            hobbies += "Ajedrez ";
        }
        if (cyoga.isChecked()) {
            hobbies += "Yoga ";
        }
        if (cnadar.isChecked()) {
            hobbies += "Nadar ";
        }



        if (rmale.isChecked()) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }

        if(hobbies.isEmpty()){
          cont=1;
        }
        if(pass.getText().toString().isEmpty() || pass2.getText().toString().isEmpty()){
            cont=1;
        }
        else {
            passw=pass.getText().toString();
            passw2=pass2.getText().toString();
            if(!passw.equals(passw2)){
                cont=2;
            }

        }
        if(fecha.isEmpty()){
            cont=1;
        }

       return  cont;
    }

}



