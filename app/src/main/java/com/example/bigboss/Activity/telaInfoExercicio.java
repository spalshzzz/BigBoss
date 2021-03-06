package com.example.bigboss.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bigboss.DAO.ExercicioAndamentoDAO;
import com.example.bigboss.DAO.ExercicioConcluidoDAO;
import com.example.bigboss.DAO.UsuarioDAO;
import com.example.bigboss.Model.ExercicioConcluido;
import com.example.bigboss.Model.Usuario;
import com.example.bigboss.R;

import com.example.bigboss.Model.ExercicioAndamento;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class telaInfoExercicio extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textProgressBar;
    TextView nomeExercicio;
    TextView series;
    TextView metricaValor;
    TextView diasRestantes;
    TextView lembrete;
    TextView descricaoExercicio;
    ExercicioAndamento exercicioAndamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_info_exercicio);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Informação Exercício");

        exercicioAndamento = getIntent().getParcelableExtra("exercicio");

        //Barra de Progresso
        progressBar = findViewById(R.id.exercicioProgresso1);
        textProgressBar = findViewById(R.id.textExercicioProgresso1);
        diasRestantes = findViewById(R.id.textDiasRestantes);
        lembrete = findViewById(R.id.lembrete);
        nomeExercicio = findViewById(R.id.nomeExercicio);
        series = findViewById(R.id.textInfoSeries);
        metricaValor = findViewById(R.id.textInfoMetricaValor);
        descricaoExercicio = findViewById(R.id.textDescricaoExercicio);


        long end = Calendar.getInstance().getTimeInMillis();
        long start = exercicioAndamento.getDataInicio().getTimeInMillis();
        long diferenca = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
        int dias = exercicioAndamento.getNumeroDias() - (int) diferenca;
        if(dias < 0)
            dias = 0;

        nomeExercicio.setText(exercicioAndamento.getNome());
        progressBar.setProgress((100*exercicioAndamento.getQuantidadeRealizada())/exercicioAndamento.getQuantidadeObjetivo());
        textProgressBar.setText(String.valueOf(exercicioAndamento.getQuantidadeRealizada())+"/"+String.valueOf(exercicioAndamento.getQuantidadeObjetivo()));


        series.setText("Series: " + String.valueOf(exercicioAndamento.getSerie()));
        metricaValor.setText(exercicioAndamento.getMetrica() + ": " + String.valueOf(exercicioAndamento.getQuantidadeMetrica()));
        diasRestantes.setText(String.valueOf(dias) + " dias Restantes");
        descricaoExercicio.setText(exercicioAndamento.getDescricao());

        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, exercicioAndamento.getHora(), exercicioAndamento.getMinuto());
        lembrete.setText(android.text.format.DateFormat.format("HH:mm", calendar));

        Button button;
        button = findViewById(R.id.buttonDomingo);
        if(!exercicioAndamento.isDomingo()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSegunda);
        if(!exercicioAndamento.isSegunda()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonTerca);
        if(!exercicioAndamento.isTerca()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonQuarta);
        if(!exercicioAndamento.isQuarta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonQuinta);
        if(!exercicioAndamento.isQuinta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSexta);
        if(!exercicioAndamento.isSexta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSabado);
        if(!exercicioAndamento.isSabado()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }

    }


    public void editarExercicio(View view){
        Intent intent = new Intent(this, telaEditarExercicio.class);
        intent.putExtra("exercicio", exercicioAndamento);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public void excluirExercicio(View view){
        Intent intent = new Intent(this, telaInicio.class);
        ExercicioAndamentoDAO exercicioAndamentoDAO = new ExercicioAndamentoDAO(getApplicationContext());
        exercicioAndamentoDAO.ExcluirExercicio(exercicioAndamento.getCodigo());
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public void updateExercicio(View view){
        ExercicioAndamentoDAO exercicioAndamentoDAO = new ExercicioAndamentoDAO(getApplicationContext());
        ExercicioConcluidoDAO exercicioConcluidoDAO = new ExercicioConcluidoDAO(getApplicationContext());
        if(exercicioAndamento.getQuantidadeRealizada() < exercicioAndamento.getQuantidadeObjetivo()){
            exercicioAndamento.setQuantidadeRealizada(exercicioAndamento.getQuantidadeRealizada() + 1);
            exercicioAndamentoDAO.AtualizarExercicio(exercicioAndamento);
            IncrementarXP(5);
            Intent intent = new Intent(this, telaInfoExercicio.class);
            intent.putExtra("exercicio", exercicioAndamento);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
        if(exercicioAndamento.getQuantidadeRealizada() == exercicioAndamento.getQuantidadeObjetivo()){
            ExercicioConcluido exercicioConcluido = new ExercicioConcluido(exercicioAndamento.getCodigo(), exercicioAndamento.getNome(), exercicioAndamento.getSerie(), exercicioAndamento.getMetrica(), exercicioAndamento.getQuantidadeMetrica(), exercicioAndamento.getQuantidadeObjetivo());
            exercicioAndamentoDAO.ExcluirExercicio(exercicioAndamento.getCodigo());
            exercicioConcluidoDAO.InserirExercicio(exercicioConcluido);
            IncrementarXP(30);
            Intent intent = new Intent(this, telaInicio.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
    }

    public void IncrementarXP(int quantidade){
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        Usuario usuario = usuarioDAO.buscarUsuario();

        usuario.setXp(usuario.getXp() + quantidade);
        if(usuario.getXp() >= usuario.getLevel() * 100){
            usuario.setXp(usuario.getXp() -  usuario.getLevel() * 100);
            usuario.setLevel(usuario.getLevel() + 1);

            if(usuario.getLevel() <= 2)
                usuario.setDivisao("Frango");
            else if(usuario.getLevel() <= 5)
                usuario.setDivisao("Junior");
            else if(usuario.getLevel() <= 10)
                usuario.setDivisao("Bombadinho");
            else if(usuario.getLevel() <= 15)
                usuario.setDivisao("Maromba");
            else if(usuario.getLevel() <= 20)
                usuario.setDivisao("Monstro");
            else
                usuario.setDivisao("BIG BOSS");
        }
        usuarioDAO.AtualizarUsuario(usuario);
    }
}