# Seu Treino App

## Requisitos funcionais
### Gerenciamento de Treinos:
- [x] Deve ser possível criar um treino*
  - [x] Definir nome, descrição e exercícios do treino.*
  - [x] Salvar os treinos em um banco de dados Firebase Firestore.*

- [x] Deve ser possível visualizar treinos existentes.*
  - [x] Listar todos os treinos criados.*
  - [x] Visualizar os detalhes de cada treino.*
  - [ ] Filtrar e pesquisar treinos por nome ou data.

- [x] Deve ser possível iniciar um treino*
  - [x] Cada quantidade de tempo de um treino deve passar por um countdown, até completar todos os treinos

- [x] Deve ser possível excluir treinos existentes.*
  - [x] Remover um treino completo da lista de treinos no banco de dados.*

### Gerenciamento de Exercícios:
- [x] Deve ser possível criar um exercício*
  - [x] Definir nome, descrição e imagem(opcional).*
  - [x] Salvar os exercícios em um banco de dados Firebase Firestore.*

- [x] Deve ser possível visualizar exercícios existentes.*
  - [x] Listar todos os exercícios criados.*
  - [x] Visualizar os detalhes de cada exercício.*
  - [ ] Filtrar e pesquisar exercícios por nome.

- [x] Deve ser possível editar exercícios existentes.*
  - [x] Modificar qualquer informação de um exercício.*
  - [x] Salvar alterações no banco de dados.*

- [x] Deve ser possível excluir exercícios existentes.*
  - [x] Remover um exercício completo da lista de exercícios no banco de dados.*

### Associação de Treinos e Exercícios
- [x] Ao criar um treino, permitir que o usuário selecione exercícios da lista de exercícios criados.
- [x] Salvar a associação entre o treino e os exercícios selecionados no banco de dados.
- [x] Ao visualizar um treino, mostrar a lista de exercícios associados a ele.


## Regras de negócio
- [ ] Não deve ser possível criar um treino sem a existência de exercícios.
- [ ] Ao criar um treino, o usuário deve selecionar pelo menos um exercício da biblioteca de exercícios.
- [ ] A quantidade de exercícios por treino é limitada (5 exercícios por treino).

## Organização
![img.png](img.png)

## TODO
Vários problemas inesperados afetaram a aplicação, então algumas coisas não foram completadas. Elas não serão entregues no prazo limite, porém serão feitas posteriormente para adicionar o projeto ao meu portifólio.
- A data do treino deveria ser implementada após iniciar um treino. Esse histórico de treinos deveria ter sido salvo no banco de dados Firestore, e então exibida uma tela de histórico. No estado atual da aplicação, não há histórico de exercícios feitos, há apenas como fazer exercícios sem salvar o histórico.
- A edição de um treino não foi implementada. É possível apenas criar, utilizar, e deletar um treino. Outras funcionalidades foram priorizadas, como o countdown dos exercícios de um treino.

Entendo que a incompletude dos requisitos mencionados acima pode afetar minha chance de aprovação, porém creio que as coisas a mais que fiz compensem essa falta. Ainda essa semana eu com certeza vou terminar toda a aplicação, e mesmo que eu não seja aprovado, tenho certeza que esse app me proporcionou grande evolução e que sua presença no meu portfólio será engrandecedora. Obrigado dese já!