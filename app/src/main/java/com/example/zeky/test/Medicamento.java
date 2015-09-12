package com.example.zeky.test;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

/**
 * Created by Zeky on 19/3/15.
 */
public class Medicamento extends BroadcastReceiver implements Parcelable{
    //atributos
    private String nMedicamento;
    private int miligramos;
    private int vecesAlDia;
    private int tomas;
    private int totalTomas;
    private long siguienteHora;
    private int ALARM_REQUEST_CODE = 1;

    public static final Parcelable.Creator<Medicamento> CREATOR = new ClassLoaderCreator<Medicamento>() {
        @Override
        public Medicamento createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Medicamento[] newArray(int size) {
            return new Medicamento[size];
        }

        @Override
        public Medicamento createFromParcel(Parcel source, ClassLoader loader) {
            return new Medicamento(source);
        }
    };

    public Medicamento(String nM, int mg, int veces){
        this.nMedicamento = nM;
        this.miligramos = mg;
        this.vecesAlDia = veces;
    }

    public Medicamento(){
    }

    public Medicamento(Parcel source) {
        this.nMedicamento = source.readString();
        this.tomas = source.readInt();
        this.totalTomas = source.readInt();
        this.siguienteHora = source.readLong();
    }

    //metodos getter y setter
    public void setnMedicamento(String m){
        this.nMedicamento = m;
    }

    public String getnMedicamento() {
        return this.nMedicamento;
    }

    public void setMiligramos(int mg){
        this.miligramos = mg;
    }

    public int getMiligramos(){
        return this.miligramos;
    }

    public void setTomas(int tmas){
        this.tomas = tmas;
    }

    public int getTomas(){
        return this.tomas;
    }

    public int getTotalTomas(){
        return this.totalTomas;
    }

    public void setVecesAlDia(int vecesAlDia){
        this.vecesAlDia = vecesAlDia;
    }

    public long getSiguienteHora(){
        return this.siguienteHora;
    }

    //metodo para calcular la cantidad total de tomas

    public void calcularTotalTomas(int dias){
        this.totalTomas = this.vecesAlDia * dias;
        this.tomas = totalTomas -1 ;
    }

    //horas que pasan entre toma y toma
    public int calcularHoras(){
        return 24 / this.vecesAlDia;
    }

    public void realizarToma(){
        this.tomas--;
    }

    public void calcularSiguienteToma(){
        long ahora = System.currentTimeMillis();
        long siguiente = ahora + (calcularHoras() * 60 * 60 * 1000);
        this.siguienteHora = siguiente;
    }

    public void añadirAlarma(long siguienteHora, Context c){
        AlarmManager manager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(c, this.getClass());
        PendingIntent pIntnet = PendingIntent.getBroadcast(c.getApplicationContext(), ALARM_REQUEST_CODE, i , PendingIntent.FLAG_CANCEL_CURRENT);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + siguienteHora, pIntnet);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        realizarToma();
        Toast.makeText(context,"quedan " + this.tomas, Toast.LENGTH_LONG).show();
        //REalizar el metodo para la siguiente alarma
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nMedicamento);
        dest.writeInt(this.tomas);
        dest.writeInt(this.totalTomas);
        dest.writeLong(this.siguienteHora);
    }
}
