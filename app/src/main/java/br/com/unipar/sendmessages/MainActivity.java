package br.com.unipar.sendmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unipar.sendmessages.util.Sms;
import br.com.unipar.sendmessages.util.Whats;

public class MainActivity extends AppCompatActivity {

    private EditText edPhoneNumber, edMessage;
    private String phoneNumber, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPhoneNumber = (EditText) findViewById(R.id.edPhoneNumber);
        edMessage = (EditText) findViewById(R.id.edMessage);

        this.phoneNumber = edPhoneNumber.getText().toString();
        this.message = edMessage.getText().toString();
    }

    public void send(View view) {

        if (message.length() > 160) {
            Toast.makeText(this, "O tamanho da mensagem deve ser de no máximo 160 caracteres.", Toast.LENGTH_LONG).show();
            return;
        }

        if (phoneNumber.length() < 7 || phoneNumber.length() > 13) {
            Toast.makeText(this, "Número de telefone inválido.", Toast.LENGTH_LONG).show();
            return;
        }

        Sms.send(this, phoneNumber, message);
    }

    public void sendWhats(View view) {
        try {
            Whats.sendWhats(this, message);
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
