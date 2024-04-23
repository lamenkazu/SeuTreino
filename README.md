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
Durante o desenvolvimento, enfrentei alguns problemas inesperados que impactaram a conclusão de algumas funcionalidades. Infelizmente, algumas delas não foram concluídas dentro do prazo estabelecido. No entanto, quero assegurar que essas pendências serão tratadas e implementadas posteriormente para enriquecer ainda mais o meu portfólio com este projeto, e que não foram feitas por falta de capacidade.

- Uma das funcionalidades que não foi concluída conforme planejado é a integração da data de treinamento, que deveria ser registrada após iniciar um treino. Além disso, o histórico de treinamentos, que deveria ser armazenado no banco de dados Firestore e posteriormente exibido em uma tela dedicada, ainda não está disponível na aplicação. Até o momento, apenas a funcionalidade de realizar exercícios sem a devida persistência do histórico está implementada.

- Outro ponto que não foi finalizado é a capacidade de editar um treino já criado. Atualmente, somente é possível criar, utilizar e excluir um treino. Isso se deve ao fato de ter sido priorizado outras funcionalidades, como o cronômetro para os exercícios dentro de um treino.

Reconheço que a falta de conclusão desses requisitos pode influenciar negativamente na minha avaliação para esta oportunidade. No entanto, estou confiante de que os esforços adicionais que investi nesta aplicação compensam essas lacunas. Estou comprometido a concluir todas as pendências até o final desta semana. Mesmo que não seja selecionado para avançar neste processo seletivo, acredito que este projeto representou uma significativa evolução para mim, e estou certo de que sua inclusão no meu portfólio será valorizada. Agradeço desde já pela compreensão e pela oportunidade!
