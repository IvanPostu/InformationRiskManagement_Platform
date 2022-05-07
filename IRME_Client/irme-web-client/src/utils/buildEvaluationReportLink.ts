import base64 from './base64'

export function buildEvaluationReportLink(
  processId: number,
  organisationName: string,
  categoryName: string,
  docType: 'docx' | 'doc'
): string {
  return `http://localhost:8080/api/securityAssessment/download?processId=${processId}&organisationName=${base64.encode(
    organisationName
  )}&categoryName=${base64.encode(categoryName)}&docType=${docType}`
}
