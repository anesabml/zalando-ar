package com.anesabml.zalando.ui.productArScene

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.FragmentArSceneBinding
import com.anesabml.zalando.extensions.viewBinding
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import timber.log.Timber

class ProductArFragment : Fragment(R.layout.fragment_ar_scene) {

    private val binding: FragmentArSceneBinding by viewBinding(FragmentArSceneBinding::bind)
    private lateinit var productModelRenderable: ModelRenderable
    private var hasFinishedLoading: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        val requestPermissionLauncher =
            requireActivity().registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    loadProductAsset()
                    setupArScene()
                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.camera_permission_denied,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

        if (
            ContextCompat
                .checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        ) {
            loadProductAsset()
            setupArScene()
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun setupArScene() {
        val arFragment =
            childFragmentManager.findFragmentById(R.id.product_ar_fragment) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            if (!hasFinishedLoading) {
                return@setOnTapArPlaneListener
            }

            // Create the anchor (position)
            val anchorNode = AnchorNode(hitResult.createAnchor())
            anchorNode.setParent(arFragment.arSceneView.scene)

            // Create a transformable
            TransformableNode(arFragment.transformationSystem).apply {
                renderable = productModelRenderable
                // Set the min and max scales of the ScaleController.
                // Default min is 0.75, default max is 1.75.
                scaleController.minScale = 0.4f
                scaleController.maxScale = 2.0f
                // Set the local scale of the node BEFORE setting its parent
                localScale = Vector3(0.55f, 0.55f, 0.55f)
                setParent(anchorNode)
            }.select()
        }
    }

    private fun loadProductAsset() {
        ModelRenderable.builder()
            .setSource(requireContext(), Uri.parse("T-shirt.sfb"))
            .build()
            .thenAccept { renderable ->
                productModelRenderable = renderable
                hasFinishedLoading = true
            }
            .exceptionally {
                Timber.e(it)
                Toast.makeText(requireContext(), R.string.error_try_again, Toast.LENGTH_SHORT)
                    .show()
                return@exceptionally null
            }
    }
}