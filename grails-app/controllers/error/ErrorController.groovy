package error

class ErrorController {

    def forbidden() {
        render(view: '/error/403')
    }

    def notFound() {
        render(view: '/error/404')
    }

    def internalServerError() {
        render(view: '/error/500')
    }
}
