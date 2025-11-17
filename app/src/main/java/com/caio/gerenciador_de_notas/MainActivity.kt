package com.caio.gerenciador_de_notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caio.gerenciador_de_notas.ui.theme.GerenciadorNotasTheme
import com.caio.gerenciador_de_notas.ui.theme.GradientCard
import com.caio.gerenciador_de_notas.ui.theme.PrimaryButton

//Classe aluno
class Aluno(
    nome: String = "",
    notas: List<Double> = emptyList()
) {
    var nome by mutableStateOf(nome)
    val notas = notas.toMutableStateList()

    val media: Double
        get() = if (notas.size == 3) notas.average() else 0.0

    val status: String
        get() = when {
            media < 6.0 -> "Reprovado"
            media <= 9.0 -> "Aprovado"
            else -> "Ótimo Aproveitamento"
        }

    fun adicionarNota(nota: Double): Boolean {
        if (notas.size < 3 && nota in 0.0..10.0) {
            notas.add(nota)
            return true
        }
        return false
    }

    fun limparNotas() {
        notas.clear()
    }

    fun todasNotasInseridas(): Boolean {
        return notas.size == 3
    }

    fun todasNotasValidas(): Boolean {
        return notas.size == 3 && notas.all { it in 0.0..10.0 }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadorNotasTheme {
                GerenciadorNotasApp()
            }
        }
    }
}

@Composable
fun GerenciadorNotasApp() {
    var aluno by remember { mutableStateOf(Aluno()) }
    var nomeAluno by remember { mutableStateOf("") }
    var notaTP1 by remember { mutableStateOf("") }
    var notaTP2 by remember { mutableStateOf("") }
    var notaTP3 by remember { mutableStateOf("") }

    fun resetarSistema() {
        aluno = Aluno()
        nomeAluno = ""
        notaTP1 = ""
        notaTP2 = ""
        notaTP3 = ""
    }

    LaunchedEffect(notaTP1, notaTP2, notaTP3) {
        val notasValidas = listOf(notaTP1, notaTP2, notaTP3).map {
            it.toDoubleOrNull() ?: return@map null
        }

        if (notasValidas.all { it != null && it in 0.0..10.0 }) {
            aluno.limparNotas()
            notasValidas.forEach { aluno.adicionarNota(it!!) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gestão de Notas do Caio",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        GradientCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Cadastro do Aluno",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = nomeAluno,
                    onValueChange = { nomeAluno = it },
                    label = {
                        Text(
                            "Nome completo",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                PrimaryButton(
                    onClick = {
                        if (nomeAluno.isNotBlank()) {
                            aluno = Aluno(nomeAluno)
                            notaTP1 = ""
                            notaTP2 = ""
                            notaTP3 = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cadastrar Aluno")
                }
            }
        }

        if (aluno.nome.isNotBlank()) {
            GradientCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Lançamento de Notas",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Aluno: ${aluno.nome}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = notaTP1,
                        onValueChange = {
                            if (it.isEmpty() || it.toDoubleOrNull()?.let { nota ->
                                    nota in 0.0..10.0 } == true) {
                                notaTP1 = it
                            }
                        },
                        label = {
                            Text(
                                "Nota TP1 (0-10)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = notaTP1.isNotBlank() && notaTP1.toDoubleOrNull()?.let {
                            it !in 0.0..10.0 } == true,
                        supportingText = {
                            if (notaTP1.isNotBlank() && notaTP1.toDoubleOrNull()?.let {
                                    it !in 0.0..10.0 } == true) {
                                Text("Nota deve ser entre 0 e 10")
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = notaTP2,
                        onValueChange = {
                            if (it.isEmpty() || it.toDoubleOrNull()?.let { nota ->
                                    nota in 0.0..10.0 } == true) {
                                notaTP2 = it
                            }
                        },
                        label = {
                            Text(
                                "Nota TP2 (0-10)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = notaTP2.isNotBlank() && notaTP2.toDoubleOrNull()?.let {
                            it !in 0.0..10.0 } == true,
                        supportingText = {
                            if (notaTP2.isNotBlank() && notaTP2.toDoubleOrNull()?.let {
                                    it !in 0.0..10.0 } == true) {
                                Text("Nota deve ser entre 0 e 10")
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = notaTP3,
                        onValueChange = {
                            if (it.isEmpty() || it.toDoubleOrNull()?.let { nota ->
                                    nota in 0.0..10.0 } == true) {
                                notaTP3 = it
                            }
                        },
                        label = {
                            Text(
                                "Nota TP3 (0-10)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = notaTP3.isNotBlank() && notaTP3.toDoubleOrNull()?.let {
                            it !in 0.0..10.0 } == true,
                        supportingText = {
                            if (notaTP3.isNotBlank() && notaTP3.toDoubleOrNull()?.let {
                                    it !in 0.0..10.0 } == true) {
                                Text("Nota deve ser entre 0 e 10")
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                }
            }

            GradientCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Resultado Final",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (notaTP1.isNotBlank() || notaTP2.isNotBlank() || notaTP3.isNotBlank()) {
                        Text(
                            text = "Notas do aluno:",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = "TP1: ${notaTP1.ifBlank { "---" }}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = when {
                                    notaTP1.isBlank() -> MaterialTheme.colorScheme.onSurfaceVariant
                                    notaTP1.toDoubleOrNull()?.let { it !in 0.0..10.0 } == true ->
                                        MaterialTheme.colorScheme.error
                                    else -> MaterialTheme.colorScheme.onSurface
                                }
                            )
                            Text(
                                text = "TP2: ${notaTP2.ifBlank { "---" }}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = when {
                                    notaTP2.isBlank() -> MaterialTheme.colorScheme.onSurfaceVariant
                                    notaTP2.toDoubleOrNull()?.let { it !in 0.0..10.0 } == true ->
                                        MaterialTheme.colorScheme.error
                                    else -> MaterialTheme.colorScheme.onSurface
                                }
                            )
                            Text(
                                text = "TP3: ${notaTP3.ifBlank { "---" }}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = when {
                                    notaTP3.isBlank() -> MaterialTheme.colorScheme.onSurfaceVariant
                                    notaTP3.toDoubleOrNull()?.let { it !in 0.0..10.0 } == true ->
                                        MaterialTheme.colorScheme.error
                                    else -> MaterialTheme.colorScheme.onSurface
                                }
                            )
                        }
                    }

                    if (aluno.todasNotasValidas()) {
                        Text(
                            text = "Cálculo da Média:",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "(${"%.1f".format(aluno.notas[0])} + ${"%.1f".format(aluno.notas[1])} + ${"%.1f".format(aluno.notas[2])}) ÷ 3",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = "= ${"%.1f".format(aluno.notas[0] + aluno.notas[1] + aluno.notas[2])} ÷ 3",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = "= ${"%.1f".format(aluno.media)}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        Text(
                            text = "Média Final: ${"%.1f".format(aluno.media)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        val (statusText, statusColor, statusEmoji) = when (aluno.status) {
                            "Reprovado" -> Triple("Reprovado", MaterialTheme.colorScheme.error, "🔴")
                            "Aprovado" -> Triple("Aprovado", MaterialTheme.colorScheme.primary, "🟢")
                            "Ótimo Aproveitamento" -> Triple("Ótimo Aproveitamento", MaterialTheme.colorScheme.tertiary, "🟣")
                            else -> Triple(aluno.status, MaterialTheme.colorScheme.onSurface, "⚪")
                        }

                        Text(
                            text = "$statusEmoji Status: $statusText",
                            style = MaterialTheme.typography.headlineMedium,
                            color = statusColor,
                            fontSize = 20.sp
                        )
                    } else {
                        val mensagem = when {
                            listOf(notaTP1, notaTP2, notaTP3).any {
                                it.isNotBlank() && it.toDoubleOrNull()?.let { nota ->
                                    nota !in 0.0..10.0 } == true
                            } -> "Corrija as notas inválidas. Mantenha-se entre 0 e 10!"
                            aluno.todasNotasInseridas() && !aluno.todasNotasValidas() ->
                                "Algumas notas estão fora do range permitido (0 a 10)"
                            else -> "Digite as 3 notas corretamente para ver o resultado"
                        }

                        Text(
                            text = mensagem,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
            }

            PrimaryButton(
                onClick = { resetarSistema() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Reiniciar")
            }
        } else {
            Text(
                text = "Cadastre seu nome para começar!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 32.dp)
            )
        }
    }
}