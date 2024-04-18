# Seu Treino App

## Requisitos funcionais
### Gerenciamento de Treinos:
- [ ] Deve ser possível criar um treino*
      - [ ] Definir nome, descrição e data do treino.*
      - [ ] Selecionar grupos musculares principais.
      - [ ] Definir séries, repetições e carga para cada exercício.
      - [ ] Salvar os treinos em um banco de dados Firebase Firestore.*
- [ ] Deve ser possível visualizar treinos existentes.*
      - [ ] Listar todos os treinos criados.*
      - [ ] Visualizar os detalhes de cada treino.*
      - [ ] Filtrar e pesquisar treinos por nome ou data.
- [ ] Deve ser possível editar treinos existentes.*
      - [ ] Modificar qualquer informação de um treino.*
      - [ ] Salvar alterações no banco de dados.*
- [ ] Deve ser possível excluir treinos existentes.*
      - [ ] Remover um treino completo da lista de treinos no banco de dados.*
      - [ ] Garantir que a exclusão não afete outros treinos ou dados relacionados.*

### Gerenciamento de Exercícios:
- [ ] Deve ser possível criar um exercício*
      - [ ] Definir nome, descrição e imagem(opcional).*
      - [ ] Salvar os exercícios em um banco de dados Firebase Firestore.*
- [ ] Deve ser possível visualizar exercícios existentes.*
      - [ ] Listar todos os exercícios criados.*
      - [ ] Visualizar os detalhes de cada exercício.*
      - [ ] Filtrar e pesquisar exercícios por nome.
- [ ] Deve ser possível editar exercícios existentes.*
      - [ ] Modificar qualquer informação de um exercício.*
      - [ ] Salvar alterações no banco de dados.*
- [ ] Deve ser possível excluir exercícios existentes.*
      - [ ] Remover um exercício completo da lista de exercícios no banco de dados.*
      - [ ] Garantir que a exclusão não afete outros exercícios ou dados relacionados.*

### Associação de Treinos e Exercícios
- [ ] Ao criar um treino, permitir que o usuário selecione exercícios da lista de exercícios criados.
- [ ] Salvar a associação entre o treino e os exercícios selecionados no banco de dados.
- [ ] Ao visualizar um treino, mostrar a lista de exercícios associados a ele.


## Regras de negócio
- [ ] Ao criar um treino, o usuário deve selecionar pelo menos um exercício da biblioteca de exercícios.
- [ ] A quantidade de exercícios por treino não é limitada.

