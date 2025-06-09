const form = document.getElementById('form-jogo');
const lista = document.getElementById('lista-jogos');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const dados = {
    titulo: form.titulo.value,
    genero: form.genero.value,
    plataforma: form.plataforma.value,
    anoLancamento: form.anoLancamento.value,
    capaUrl: form.capaUrl.value
  };

  const resposta = await fetch('http://localhost:8080/jogos', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dados)
  });

  if (resposta.ok) {
    form.reset();
    carregarJogos();
  } else {
    alert('Erro ao salvar jogo');
  }
});

async function carregarJogos() {
  const resposta = await fetch('http://localhost:8080/jogos');
  const jogos = await resposta.json();

  lista.innerHTML = '';
  jogos.forEach(jogo => {
    const div = document.createElement('div');
    div.className = 'jogo';
    div.innerHTML = `
      <h3>${jogo.titulo} (${jogo.anoLancamento})</h3>
      <p>Gênero: ${jogo.genero}</p>
      <p>Plataforma: ${jogo.plataforma}</p>
      ${jogo.capaUrl ? `<img src="${jogo.capaUrl}" alt="Capa" width="150">` : ''}
      <br>
      <button onclick="excluirJogo(${jogo.id})">Excluir</button>
    `;
    lista.appendChild(div);
  });
}

async function excluirJogo(id) {
  const confirmar = confirm("Tem certeza que deseja excluir este jogo?");
  if (!confirmar) return;

  const resposta = await fetch(`http://localhost:8080/jogos/${id}`, {
    method: 'DELETE'
  });

  if (resposta.ok) {
    carregarJogos();
  } else {
    alert('Erro ao excluir jogo');
  }
}

carregarJogos();

