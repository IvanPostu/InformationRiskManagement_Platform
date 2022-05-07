import base64 from './base64'

export function buildEvaluationReportPageLink(
  evaluationProcessId: number,
  categoryId: number,
  organisationId: number,
  organisationName: string,
  categoryName: string
): string {
  return `/evaluationReport?processId=${evaluationProcessId}&categoryId=${categoryId}&organisationId=${organisationId}&organisationName=${base64.encode(
    organisationName
  )}&categoryName=${base64.encode(categoryName)}`
}
