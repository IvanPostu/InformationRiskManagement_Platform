/* eslint-disable @typescript-eslint/no-var-requires */
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const webpack = require('webpack');
const pkg = require('../package.json');

const SRC_PATH = path.resolve(__dirname, '..', 'src');
const BUILD_PATH = path.resolve(__dirname, '..', 'build', 'app');
const MAIN_TEMPLATE_PATH = path.resolve(SRC_PATH, 'app', 'templates', 'main-template.html');

const sassRegex = /\.(scss|sass)$/;
const sassModuleRegex = /\.module\.(scss|sass)$/;
const cssRegex = /\.css$/;
const cssModuleRegex = /\.module\.css$/;

const isDev = process.env.NODE_ENV === 'development';
const isProd = !isDev;

module.exports = {
    mode: process.env.NODE_ENV,
    devtool: isDev ? 'source-map' : false,
    target: 'electron-renderer',
    entry: {
        main: path.resolve(SRC_PATH, 'app', 'index.ts'),
    },

    output: {
        path: BUILD_PATH,
        filename: '[name].bundle.js',
        chunkFilename: '[name]_chunk.js',
    },

    resolve: {
        extensions: ['.ts', '.tsx', '.js'],
        alias: {
            '@': SRC_PATH,
        },
    },

    devServer: isDev
        ? {
              compress: true,
              port: 8000,
              hot: true,
              liveReload: false,
              headers: {
                  'Access-Control-Allow-Origin': '*',
                  'Access-Control-Allow-Methods': '*',
                  'Access-Control-Allow-Headers': '*',
              },
          }
        : {},

    module: {
        rules: [
            {
                test: /\.(js|jsx|ts|tsx)$/,
                include: /src/,
                use: [{ loader: 'ts-loader' }],
            },

            {
                test: /\.(?:ico|gif|png|jpg|jpeg)$/i,
                type: 'asset/resource',
            },

            {
                test: /\.(woff(2)?|eot|ttf|otf|svg|)$/,
                type: 'asset/inline',
            },

            {
                test: sassRegex,
                exclude: sassModuleRegex,
                use: [
                    {
                        loader: isProd ? MiniCssExtractPlugin.loader : 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                    },
                    'sass-loader',
                ],
            },
            {
                test: sassModuleRegex,
                use: [
                    {
                        loader: isProd ? MiniCssExtractPlugin.loader : 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            modules: {
                                localIdentName: isDev ? '[path][name]__[local]' : '[hash:base64]',
                            },
                            sourceMap: isDev,
                        },
                    },
                    'sass-loader',
                ],
            },
            {
                test: cssRegex,
                exclude: cssModuleRegex,
                use: [
                    {
                        loader: isProd ? MiniCssExtractPlugin.loader : 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                    },
                ],
            },
            {
                test: cssModuleRegex,
                use: [
                    {
                        loader: isProd ? MiniCssExtractPlugin.loader : 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            modules: {
                                localIdentName: isDev ? '[path][name]__[local]' : '[hash:base64]',
                            },
                            sourceMap: isDev,
                        },
                    },
                ],
            },
        ],
    },

    plugins: [
        new HtmlWebpackPlugin({
            title: pkg.build.productName,
            template: MAIN_TEMPLATE_PATH,
            filename: 'index.html', // output file
        }),
        new MiniCssExtractPlugin(),
        new CleanWebpackPlugin(),
        new webpack.DefinePlugin({
            APPLICATION_VERSION: JSON.stringify(pkg.version),
            APPLICATION_NAME: JSON.stringify(pkg.build.applicationName),
            APPLICATION_SHORT_NAME: JSON.stringify(pkg.build.applicationShortName),
            BACKEND_URL: JSON.stringify(process.env.BACKEND_URL),
        }),

        ...(isDev ? [new webpack.HotModuleReplacementPlugin()] : []),
    ],

    ...(isProd
        ? {
              optimization: {
                  splitChunks: {
                      cacheGroups: {
                          commons: {
                              test: /[\\/]node_modules[\\/]/,
                              name: 'vendors',
                              chunks: 'all',
                          },
                      },
                  },
              },
          }
        : {}),
};
