let idEditando = null;
const form = document.getElementById('form-jogo');
const lista = document.getElementById('lista-jogos');
const botaoSubmit = document.getElementById('btn-submit');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const dados = {
    titulo: form.titulo.value,
    genero: form.genero.value,
    plataforma: form.plataforma.value,
    anoLancamento: form.anoLancamento.value,
    capaUrl: form.capaUrl.value
  };

  const url = idEditando
    ? `http://localhost:8080/jogos/${idEditando}`
    : `http://localhost:8080/jogos`;

  const metodo = idEditando ? 'PUT' : 'POST';

  const resposta = await fetch(url, {
    method: metodo,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dados)
  });

  if (resposta.ok) {
    form.reset();
    carregarJogos();
    idEditando = null;
    botaoSubmit.textContent = 'Adicionar Jogo'; // Volta ao modo criação
  } else {
    alert(`Erro ao ${idEditando ? 'editar' : 'salvar'} jogo`);
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
      <br>
      <button onclick="editarJogo(${jogo.id})">Editar</button>
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

async function editarJogo(id) {
  const resposta = await fetch(`http://localhost:8080/jogos/${id}`);
  if (!resposta.ok) {
    alert('Erro ao carregar dados do jogo para edição');
    return;
  }

  const jogo = await resposta.json();

  form.titulo.value = jogo.titulo;
  form.genero.value = jogo.genero;
  form.plataforma.value = jogo.plataforma;
  form.anoLancamento.value = jogo.anoLancamento;
  form.capaUrl.value = jogo.capaUrl;

  idEditando = id;
  botaoSubmit.textContent = 'Atualizar Jogo';
}

carregarJogos();
