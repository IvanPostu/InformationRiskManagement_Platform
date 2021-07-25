Cypress.Commands.add('irme_admin_mvc__login', (login, password) => {
  cy.fixture('_env.json').then(data => {
    cy.visit(`${data.adminMvcAppUrl}/login`)
    
    cy.get('#usernameFormInput')
      .type(login).should('have.value', login)

    cy.get('#passwordFormInput')
      .type(password).should('have.value', password)

    cy.get('#submitLoginId').click()

  })
})