
context('Login account', () => {
  beforeEach(() => {
    Cypress.Cookies.debug(true)

    // clear cookies again after visiting to remove
    // any 3rd party cookies picked up such as cloudflare
    cy.clearCookies()
  })

  it('Invalid username or password login test case', () => {

    cy.fixture('irme_admin_mvc/loginData.json').then(data => {
      cy.irme_admin_mvc__login(data.invalidUsername, data.invalidPassword)
      cy.get('[style="color: red;"]').should('contain', 'Invalid username or password.')
      cy.getCookies().should((cookies) => {
        expect(cookies[0].name).equal('JSESSIONID')
      })
    })

  })

  it('Valid username or password login test case', () => {
    cy.fixture('irme_admin_mvc/loginData.json').then(data => {
      cy.irme_admin_mvc__login(data.validUsername, data.validPassword)
      
      cy.url().should('eq', location.origin + '/home')
      
      cy.getCookies().should((cookies) => {
        expect(cookies[0].name).equal('JSESSIONID')
      })
    })
    
  })

})