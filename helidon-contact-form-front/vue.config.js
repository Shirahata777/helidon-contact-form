module.exports = {
    runtimeCompiler: true,
    devServer: {
        proxy: {
            '/api/': {
                target: "http://helidon-contact-form-server:3000",
                timeout: 6000,
                secure: false,
                changeOrigin: true,
            }
        }
    },
};