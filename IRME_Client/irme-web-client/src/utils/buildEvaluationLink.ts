import base64 from './base64'

export function buildEvaluationLink(
  processId: number,
  categoryId: number,
  organisationId: number,
  organisationName: string,
  categoryName: string
): string {
  return `/evaluation?processId=${processId}&categoryId=${categoryId}&organisationId=${organisationId}&organisationName=${base64.encode(
    organisationName
  )}&categoryName=${base64.encode(categoryName)}`
}
