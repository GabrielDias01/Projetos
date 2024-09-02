# Escopo Funcional

A aplicação de To-Do List deve:

1. **Cadastro e Autenticação de Usuário**
   - Permitir que usuários se registrem com um e-mail e senha.
   - Permitir que usuários façam login utilizando e-mail e senha.
   - Implementar autenticação baseada em JWT (JSON Web Token).

2. **Gerenciamento de Tarefas**
   - Permitir que usuários criem, editem e excluam tarefas.
   - Permitir que usuários visualizem uma lista de suas tarefas.
   - Permitir que usuários marquem tarefas como concluídas.

3. **Interface de Usuário**
   - Fornecer uma interface intuitiva e responsiva.
   - Implementar uma visualização clara das tarefas e seu status.
   - Implementar filtros e buscas para facilitar a navegação entre tarefas.

4. **Segurança**
   - Implementar criptografia de senhas usando bcrypt.
   - Garantir que as requisições ao servidor sejam feitas por meio de um canal seguro (HTTPS).

5. **Gerenciamento de Sessões**
   - Implementar a expiração e renovação de tokens JWT para sessões seguras.
   - Gerenciar o logout e a invalidação de tokens.

6. **Backup e Recuperação**
   - Implementar uma estratégia de backup dos dados (opcional, mas recomendável).

# Escopo Não Funcional

1. **Desempenho**
   - A aplicação deve carregar em menos de 2 segundos na maioria das conexões.
   - As requisições ao backend devem ser respondidas em menos de 500 milissegundos.

2. **Escalabilidade**
   - O sistema deve suportar pelo menos 1000 usuários simultâneos sem degradação significativa do desempenho.

3. **Segurança**
   - Utilizar HTTPS para todas as comunicações entre o cliente e o servidor.
   - Implementar práticas seguras de armazenamento e gerenciamento de senhas e tokens.

4. **Usabilidade**
   - A interface deve ser intuitiva e de fácil navegação para usuários com pouca experiência técnica.
   - O design deve ser responsivo e funcionar bem em dispositivos móveis e desktops.

5. **Manutenibilidade**
   - O código deve ser bem documentado e seguir padrões de codificação consistentes.
   - Utilizar práticas de versionamento e controle de qualidade de código (linters, testes automatizados).

6. **Disponibilidade**
   - A aplicação deve ter uma taxa de uptime de pelo menos 99.9%.

# Objetivos SMART

1. **S - Específico**
   - Desenvolver uma aplicação web de To-Do List que permita aos usuários cadastrar e gerenciar tarefas, com autenticação segura e uma interface responsiva.

2. **M - Mensurável**
   - Implementar funcionalidades de cadastro, login, criação, edição, exclusão, e visualização de tarefas.
   - Garantir que a aplicação atenda a 95% dos casos de uso descritos sem erros críticos.

3. **A - Alcançável**
   - Utilizar React para o frontend, Node.js e Express para o backend, MongoDB para o banco de dados e JWT para autenticação.

4. **R - Relevante**
   - A aplicação deve atender a um público que necessita de uma solução simples e segura para gerenciamento de tarefas diárias.

5. **T - Temporal**
   - Concluir o desenvolvimento da aplicação em 12 semanas, com marcos de progresso a cada 2 semanas.

# Cronograma

| Semana | Atividade                         |
|--------|-----------------------------------|
| 1-2    | Planejamento e Configuração        |
| 3-4    | Desenvolvimento do Backend (API)   |
| 5-6    | Desenvolvimento do Frontend        |
| 7-8    | Integração Frontend e Backend      |
| 9-10   | Testes e Correções                 |
| 11     | Implementação de Segurança e Backup|
| 12     | Preparação para Deployment         |

# Análise de Risco

1. **Risco: Falhas na Autenticação**
   - **Probabilidade:** Média
   - **Impacto:** Alto
   - **Mitigação:** Implementar testes rigorosos e revisão do código de autenticação.

2. **Risco: Desempenho Insatisfatório**
   - **Probabilidade:** Média
   - **Impacto:** Alto
   - **Mitigação:** Realizar testes de carga e otimizar consultas ao banco de dados.

3. **Risco: Problemas de Segurança**
   - **Probabilidade:** Baixa
   - **Impacto:** Alto
   - **Mitigação:** Utilizar práticas de segurança recomendadas e revisar o código com foco em vulnerabilidades.

4. **Risco: Dificuldades na Integração**
   - **Probabilidade:** Média
   - **Impacto:** Médio
   - **Mitigação:** Implementar a integração incremental e realizar testes de integração contínuos.

5. **Risco: Escalabilidade**
   - **Probabilidade:** Baixa
   - **Impacto:** Médio
   - **Mitigação:** Projetar a arquitetura para escalabilidade e utilizar serviços de monitoramento.

# Recursos

1. **Tecnológicos**
   - **Frontend:** React, Axios, React Router
   - **Backend:** Node.js, Express, JWT, bcrypt.js, Mongoose
   - **Banco de Dados:** MongoDB
   - **Deployment:** Docker, Heroku (ou Vercel/Netlify)

2. **Humanos**
   - **Desenvolvedores:** Desenvolvedor Backend, Desenvolvedor Frontend
   - **Designer:** Designer UX/UI (opcional)
   - **QA:** Testador de Qualidade (opcional)

3. **Financeiros**
   - **Serviços de Cloud:** MongoDB Atlas, serviços de deployment
   - **Ferramentas de Desenvolvimento:** IDEs, ferramentas de design (opcional)

4. **Temporais**
   - **Horas estimadas:** 300 horas totais
   - **Equipe:** 2-3 membros, dependendo da carga de trabalho.

<img src="img/DIAGRAMA DE CLASSE.PNG">
<img src="img/DIAGRAMA DE USO.PNG">
<img src="img/DIAGRAMA DE FLUXO.PNG">